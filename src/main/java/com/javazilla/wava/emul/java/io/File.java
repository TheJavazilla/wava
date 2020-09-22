package java.io;

public class File {

    public static String separator = "/";
    private String name;

    public File(String string) {
        this.name = string;
        // TODO Auto-generated constructor stub
    }
    
    public File[] listFiles() {
        return null;
    }
    
    public String getName() {
        return name;
    }

    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    public File getParentFile() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAbsolutePath() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object toPath() {
        // TODO Auto-generated method stub
        return null;
    }

    public void mkdirs() {
        // TODO Auto-generated method stub
        
    }

}
