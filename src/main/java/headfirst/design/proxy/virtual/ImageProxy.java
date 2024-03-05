package headfirst.design.proxy.virtual;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageProxy implements Icon {
    volatile ImageIcon imageIcon;
    final URL imageUrl;
    Thread retrievalThread;
    boolean retrieving = false;

    public ImageProxy(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getIconWidth() {
        if(imageIcon != null) {
            return imageIcon.getIconWidth();
        }
        return 800;
    }

    @Override
    public int getIconHeight() {
        if(imageIcon != null) {
            return imageIcon.getIconHeight();
        }
        return 600;
    }

    synchronized void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public void paintIcon(final Component c, Graphics g, int x, int y) {
        if(imageIcon != null) {
            imageIcon.paintIcon(c, g, x, y);
        } else {
            g.drawString("앨범 커버를 불러오는 중입니다. 잠시만 기다려 주세요....", x + 300, y + 190);

            if(!retrieving) {
                retrieving = true;

                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            setImageIcon(new ImageIcon(imageUrl, "Album Cover"));
                            c.repaint(); // Component method, 이미지 확보되면 호출해서 화면을 갱신
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                retrievalThread.start();
            }
        }
    }
}
