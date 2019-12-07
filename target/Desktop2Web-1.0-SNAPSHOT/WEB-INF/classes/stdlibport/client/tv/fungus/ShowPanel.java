package stdlibport.client.tv.fungus;

import stdlibport.client.java.awt.Graphics;
import stdlibport.client.java.awt.Image;
import stdlibport.client.java.awt.image.BufferedImage;
import stdlibport.client.javax.swing.JButton;
import stdlibport.client.javax.swing.JLabel;

import stdlibport.client.javax.swing.JPanel;

public class ShowPanel extends JPanel {

    private Image dm = null;
    private int h=0, w=0;
    private ImageType i;

    public enum ImageType {
        PublicDomain,
        dailymotion,
        DisneyYT,
        none,
        boomerang;
    }

    JButton b = null;
    JLabel na = null;
    public ShowPanel(String s, ImageType i) {
        /*this.i = i;
        File folder = new File(s);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        b = new JButton("<");
        b.setFont(b.getFont().deriveFont(18f));
        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel p = IsaiahTv.inst.getMenu();
                IsaiahTv.inst.setContentPane(new JScrollPane(p));
                IsaiahTv.inst.validate();
            }
        });

        this.add(b);
        this.add(Box.createVerticalStrut(20));

        for (File f : folder.listFiles()) {
            if (f.getName().contains(".png"))
                continue;
            JButton l = new JButton(f.getName() + " - VIDEO_LENGTH_LOADING");
            l.setText(l.getText().replace(".web - VIDEO_LENGTH_LOADING", ""));
            async(() -> l.setText(l.getText().replace("VIDEO_LENGTH_LOADING", getDuration(f.getAbsolutePath()))));

            boolean isYT = false;
            String vid = "";
            if (f.getName().endsWith(".web")) {
                String path = null;
                try {
                    path = Files.readAllLines(f.toPath()).get(0);
                } catch (IOException e1) {e1.printStackTrace();}
                if (path.contains("youtube.com")) {
                    isYT = true;
                    vid = path.substring(path.indexOf("embed") + "embed".length() + 1);
                }
            }

            File fi = new File(IsaiahTv.path + File.separator + "cashed" + File.separator + vid + ".jpg");
            boolean isYT2 = isYT;
            String vid2 = vid;
            async(() -> {
                ImageIcon icon = null;
            try {
                BufferedImage bi = null;
                if (IsaiahTv.map.containsKey(fi.getName())) {
                    bi = IsaiahTv.map.get(f.getName());
                } else if (fi.exists()) {
                    bi = ImageIO.read(fi);
                    IsaiahTv.map.put(f.getName(), bi);
                } else if (isYT2) {
                    bi = ImageIO.read(new URL("https://i3.ytimg.com/vi/" + vid2 + "/default.jpg"));
                    bi = toBufferedImage(bi.getScaledInstance(56, 46, 0));
                    fi.getParentFile().mkdirs();
                    ImageIO.write(bi, "JPG", fi);
                    IsaiahTv.map.put(f.getName(), bi);
                }
                icon = isYT2
                        ? new ImageIcon(bi.getScaledInstance(56, 46, 0))
                        : (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
            } catch (IOException e3) {e3.printStackTrace();}
            l.setIcon(isYT2 ? icon : new ImageIcon(icon.getImage().getScaledInstance(24, 24, 1)));
            });
            l.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            /*l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (f.getName().endsWith(".mp4") && !(f.getName().toLowerCase().contains("movie"))) {
                        //VideoPlayer p = new VideoPlayer(f.getAbsolutePath());
                        //IsaiahTv.inst.setContentPane(p);
                        //IsaiahTv.inst.validate();
                    } else if (f.getName().endsWith(".web")) {
                        String path = null;
                        try {
                            path = Files.readAllLines(f.toPath()).get(0);
                        } catch (IOException e1) {e1.printStackTrace();}
                        //WebPlayer p = new WebPlayer(path);
                        //IsaiahTv.inst.setContentPane(p);
                        //IsaiahTv.inst.validate();
                    } else {
                        try {
                            Desktop.getDesktop().open(f);
                        } catch (IOException e2) {e2.printStackTrace();}
                    }
                }
            });*
            l.setMinimumSize(new Dimension(600,50));
            l.setPreferredSize(l.getMinimumSize());
            l.setMaximumSize(l.getMinimumSize());
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.validate();
            JPanel bp = new JPanel();
            bp.add(l);
            bp.setOpaque(false);
            bp.setBorder(null);
            bp.setMinimumSize(l.getMinimumSize());
            bp.setPreferredSize(l.getMinimumSize());
            bp.setMaximumSize(l.getMinimumSize());

            this.add(bp);*/
        //}
        /*TitledBorder border = BorderFactory.createTitledBorder(s.substring(s.lastIndexOf("\\")+1));
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(border.getTitleFont().deriveFont(18f));
        this.setBorder(border);
        this.setOpaque(false);
        IsaiahTv.loading = false;*/
    }

    @Override
    public void paint(Graphics g) {
        /*if (dm == null && i != ImageType.none) {
            try {
                dm = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(i.toString() + ".png"));
                w = dm.getWidth(null);
                h = dm.getHeight(null);
            } catch (IOException e) {e.printStackTrace();}
        }
        if (i != ImageType.none)
            g.drawImage(dm, getWidth()-w, (int)IsaiahTv.inst.getBounds().getHeight()-48-h, null);
        if (b != null) {
            b.setLocation(10, 20);
            b.validate();
        }
        super.paint(g);
        repaint();*/
    }

    public static String getDuration(final String sVideoInput) {
        /*System.out.println(sVideoInput);
        if (sVideoInput.contains(".web"))
            return "";
        String res = "";
        try{
            Process child = Runtime.getRuntime().exec(IsaiahTv.path + "/ffmpeg.exe"  + " -i \"" + sVideoInput + "\"");
            BufferedReader in = new BufferedReader(new InputStreamReader(child.getErrorStream()));

            String line;
            while ((line = in.readLine()) != null){
                if(line.contains("Duration:")){
                    res = (line = line.replaceFirst("Duration: ", "").trim()).substring(0, 11);
                    break;
                }
            }
        } catch (Exception e){}

        String[] parts = res.split(":");
        if (parts.length < 2)
            return "";
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        if (m >= 19 && m <= 21) m = 20;

        return (h > 0 ? h + " hours, " : " ") + m + " minutes" + (m <= 0 ? ", " + parts[2].substring(0,2) + " seconds" : "");*/
        return "-1 minutes";
    }
    
    public void async(Runnable r) {
        //new Thread(r).start();
    }
    
    public static BufferedImage toBufferedImage(Image img) {
        //if (img instanceof BufferedImage)
        //    return (BufferedImage) img;

        // Create a buffered image with transparency
        //BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        //Graphics2D bGr = bimage.createGraphics();
        //bGr.drawImage(img, 0, 0, null);
        //bGr.dispose();

        // Return the buffered image
        //return bimage;
        return null;
    }

}