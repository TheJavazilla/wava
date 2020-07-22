package wava.sun.java2d.cmm;

import java.awt.color.ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.color.CMMException;
import java.security.AccessController;
import java.security.PrivilegedAction;
//import wava.sun.security.action.GetPropertyAction;
import java.util.ServiceLoader;

public class CMSManager {

    public static ColorSpace GRAYspace;       // These two fields allow access to java.awt.color.ColorSpace private fields 
    public static ColorSpace LINEAR_RGBspace; // from other packages. The fields are set by java.awt.color.ColorSpace and read by java.awt.image.ColorModel.

    private static PCMM cmmImpl = null;

    public static synchronized PCMM getModule() {
        if (cmmImpl != null)
            return cmmImpl;

        CMMServiceProvider spi = AccessController.doPrivileged(new PrivilegedAction<CMMServiceProvider>() {
            public CMMServiceProvider run() {
                String cmmClass = System.getProperty("wava.sun.java2d.cmm", "wava.sun.java2d.cmm.lcms.LcmsServiceProvider");

                ServiceLoader<CMMServiceProvider> cmmLoader = ServiceLoader.loadInstalled(CMMServiceProvider.class);

                CMMServiceProvider spi = null;

                for (CMMServiceProvider cmm : cmmLoader) {
                    spi = cmm;
                    if (cmm.getClass().getName().equals(cmmClass))
                        break;
                }
                return spi;
            }
        });

        cmmImpl = spi.getColorManagementModule();

        if (cmmImpl == null)
            throw new CMMException("Cannot initialize Color Management System. No CM module found");

        //GetPropertyAction gpa = new GetPropertyAction("wava.sun.java2d.cmm.trace");
        //String cmmTrace = (String)AccessController.doPrivileged(gpa);
        //if (cmmTrace != null)
        //    cmmImpl = new CMMTracer(cmmImpl);

        return cmmImpl;
    }

    static synchronized boolean canCreateModule() {
        return (cmmImpl == null);
    }

    /* CMM trace routines */

    public static class CMMTracer implements PCMM {
        PCMM tcmm;
        String cName ;

        public CMMTracer(PCMM tcmm) {
            this.tcmm = tcmm;
            cName = tcmm.getClass().getName();
        }

        public Profile loadProfile(byte[] data) {
            System.err.print(cName + ".loadProfile");
            Profile p = tcmm.loadProfile(data);
            System.err.printf("(ID=%s)\n", p.toString());
            return p;
        }

        public void freeProfile(Profile p) {
            System.err.printf(cName + ".freeProfile(ID=%s)\n", p.toString());
            tcmm.freeProfile(p);
        }

        public int getProfileSize(Profile p) {
            System.err.print(cName + ".getProfileSize(ID=" + p + ")");
            int size = tcmm.getProfileSize(p);
            System.err.println("=" + size);
            return size;
        }

        public void getProfileData(Profile p, byte[] data) {
            System.err.print(cName + ".getProfileData(ID=" + p + ") ");
            System.err.println("requested " + data.length + " byte(s)");
            tcmm.getProfileData(p, data);
        }

        public int getTagSize(Profile p, int tagSignature) {
            System.err.printf(cName + ".getTagSize(ID=%x, TagSig=%s)", p, signatureToString(tagSignature));
            int size = tcmm.getTagSize(p, tagSignature);
            System.err.println("=" + size);
            return size;
        }

        public void getTagData(Profile p, int tagSignature, byte[] data) {
            System.err.printf(cName + ".getTagData(ID=%x, TagSig=%s)", p, signatureToString(tagSignature));
            System.err.println(" requested " + data.length + " byte(s)");
            tcmm.getTagData(p, tagSignature, data);
        }

        public void setTagData(Profile p, int tagSignature, byte[] data) {
            System.err.print(cName + ".setTagData(ID=" + p + ", TagSig=" + tagSignature + ")");
            System.err.println(" sending " + data.length + " byte(s)");
            tcmm.setTagData(p, tagSignature, data);
        }

        /* methods for creating ColorTransforms */
        public ColorTransform createTransform(ICC_Profile profile, int renderType, int transformType) {
            System.err.println(cName + ".createTransform(ICC_Profile,int,int)");
            return tcmm.createTransform(profile, renderType, transformType);
        }

        public ColorTransform createTransform(ColorTransform[] transforms) {
            System.err.println(cName + ".createTransform(ColorTransform[])");
            return tcmm.createTransform(transforms);
        }

        private static String signatureToString(int sig) {
            return String.format("%c%c%c%c",
                                 (char)(0xff & (sig >> 24)),
                                 (char)(0xff & (sig >> 16)),
                                 (char)(0xff & (sig >>  8)),
                                 (char)(0xff & (sig      )));
        }
    }

}