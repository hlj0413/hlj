package com.hlj.pingtu;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];//定义在属性中方便全局调用
    String path = "com/hlj/pingtu/img/";
    String renwu = "cr1/split_";
    String wurenyuantu = "cr1/IMG_20250408_194942.jpg";
    int x = 0, y = 0;
    int[][] step = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
    int count = 0;

    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    JMenuItem item5;
    JMenuItem item6;
    JMenuItem item7;

    public GameJFrame() {
        initJframe();//界面

        initdata();//打乱图片

        initImage();//图像照片

        initJMnuBar();//菜单
    }

    private void initdata() {//生成一个无需的二维数组
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            int index = random.nextInt(arr.length);//随机在0-15中去一个数

            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();//删除已经存在的图片

        JLabel countjLabel = new JLabel("步数" + count);
        countjLabel.setBounds(0, 0, 50, 50);
        this.getContentPane().add(countjLabel);

        if (win_yn()) {
            win();
            System.out.println("win");
        }

        for (int i = 0; i < 4; i++) {//4*4 加载16张图片
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //ImageIcon icon = new ImageIcon(  path + "cr1/split_" + num +".png" );
                ImageIcon icon = new ImageIcon(path + renwu + num + ".png");

                Image image = icon.getImage();//得到加载的图片
                int width = 105;
                int hight = 105;

                Image image1 = image.getScaledInstance(width, hight, Image.SCALE_SMOOTH);
                //设置对应的大小（长和宽）
                ImageIcon icon1 = new ImageIcon(image1);
                //重新加载图片
                JLabel jLabel = new JLabel(icon1);
                //将图片放在容器中
                jLabel.setBounds(105 * j + 80, 105 * i + 100, 105, 105);
                // this.add(jLabel);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));//设置突起
                this.getContentPane().add(jLabel);
                //将图片放在界面中
            }
        }
        ImageIcon bg = new ImageIcon("com/hlj/pingtu/img/bgi.png");
        Image image3 = bg.getImage();
        int width1 = 600;
        int hight2 = 600;

        Image image4 = image3.getScaledInstance(width1, hight2, Image.SCALE_SMOOTH);
        //设置对应的大小（长和宽）
        ImageIcon icon5 = new ImageIcon(image4);
        //重新加载图片
        JLabel jLabel1 = new JLabel(icon5);
        //将图片放在容器中
        jLabel1.setBounds(0, 0, 600, 600);
        // this.add(jLabel);
        this.getContentPane().add(jLabel1);
        //将图片放在界面中

        this.getContentPane().repaint();//刷新页面
    }

    private void initJMnuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu function = new JMenu("功能");
        JMenu about = new JMenu("关于我们");
        JMenu change_img = new JMenu("更换图片");

        item1 = new JMenuItem("重新游戏");
        item1.addActionListener(this);
        item2 = new JMenuItem("重新登录");
        item2.addActionListener(this);
        item3 = new JMenuItem("关闭游戏");
        item3.addActionListener(this);
        item4 = new JMenuItem("公众号");
        item4.addActionListener(this);
        item5 = new JMenuItem("dpr");
        item5.addActionListener(this);
        item6 = new JMenuItem("cr");
        item6.addActionListener(this);

        function.add(item1);
        function.add(item2);
        function.add(item3);
        about.add(item4);
        change_img.add(item5);
        change_img.add(item6);

        jMenuBar.add(function);
        jMenuBar.add(about);
        jMenuBar.add(change_img);

        this.setJMenuBar(jMenuBar);//将菜单添加到页面中


        this.setVisible(true);//显示页面
    }

    private void initJframe() {
        this.setSize(600, 600);//设置框大小
        this.setTitle("游戏拼图");//框标题
        this.setAlwaysOnTop(true);//框置顶
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭页面就结束程序
        this.setLayout(null);//取消默认设置
        this.addKeyListener(this);//给整个界面添加键盘监听事件
    }

    private void win() {

        ImageIcon winicon = new ImageIcon("com/hlj/pingtu/img/1855.png_860.png");
        Image winimage = winicon.getImage();
        int width = 100;
        int hight = 100;
        Image winimage1 = winimage.getScaledInstance(width, hight, Image.SCALE_DEFAULT);
        ImageIcon winicon1 = new ImageIcon(winimage1);
        JLabel winjLable = new JLabel(winicon1);
        winjLable.setBounds(200, 200, 200, 200);
        this.getContentPane().add(winjLable);

//        JLabel jLabel = new JLabel(new ImageIcon("com/hlj/pingtu/img/1855.png_860.png"));
//        jLabel.setBounds(0,0,400,400);
//        this.getContentPane().add(jLabel);

    }

    private boolean win_yn() {//判断游戏是否胜利
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (step[i][j] != data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {

            System.out.println("查看原图");
            this.getContentPane().removeAll();

            ImageIcon icon = new ImageIcon("com/hlj/pingtu/img/" + wurenyuantu);
//            ImageIcon icon = new ImageIcon(  "com/hlj/pingtu/img/dpr/IMG_20250409_233145.jpg");

            //ImageIcon icon = new ImageIcon(  "com/hlj/pingtu/img/cxy/IMG_20250410_000622.jpg");
            Image image = icon.getImage();//得到加载的图片
            int width = 420;
            int hight = 420;

            Image image1 = image.getScaledInstance(width, hight, Image.SCALE_SMOOTH);
            //设置对应的大小（长和宽）
            ImageIcon icon1 = new ImageIcon(image1);
            //重新加载图片
            JLabel jLabel = new JLabel(icon1);
            jLabel.setBounds(80, 100, 420, 420);
            this.getContentPane().add(jLabel);

            ImageIcon bg = new ImageIcon("com/hlj/pingtu/img/bgi.png");
            Image image3 = bg.getImage();
            int width1 = 600;
            int hight2 = 600;

            Image image4 = image3.getScaledInstance(width1, hight2, Image.SCALE_SMOOTH);
            //设置对应的大小（长和宽）
            ImageIcon icon5 = new ImageIcon(image4);
            //重新加载图片
            JLabel jLabel1 = new JLabel(icon5);
            //将图片放在容器中
            jLabel1.setBounds(0, 0, 600, 600);
            // this.add(jLabel);
            this.getContentPane().add(jLabel1);
            //将图片放在界面中

            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (win_yn()) {
            return;
        }

        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向右移动");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            count++;
        } else if (code == 38) {
            System.out.println("向下移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            count++;
        } else if (code == 39) {
            System.out.println("向左移动");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            count++;
        } else if (code == 40) {
            System.out.println("向上移动");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            count++;
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
            initImage();
        }
        initImage();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == item1) {

            System.out.println("重新开始");
            count=0;
            initdata();
            initImage();
        } else if (source == item2) {
            System.out.println("重新登录");

        } else if (source == item3) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (source == item4) {
            System.out.println("公众号");
            JFrame gchjFrame = new JFrame();
            gchjFrame.setSize(300,300);//设置框大小
            gchjFrame.setTitle("公众号");//框标题
            gchjFrame.setAlwaysOnTop(true);//框置顶
            gchjFrame.setLocationRelativeTo(null);//居中
            gchjFrame.setDefaultCloseOperation(1);//关闭页面就结束程序
            ImageIcon gongchonghao = new ImageIcon("com/hlj/pingtu/img/dpr/IMG_20250409_233145.jpg");
            Image gch = gongchonghao.getImage();
            int width = 300;
            int hight = 300;
            Image gch1 = gch.getScaledInstance(width,hight,1);
            ImageIcon gongchonghao1 = new ImageIcon(gch1);
            JLabel gongchonghaojLble = new JLabel(gongchonghao1);
            gongchonghaojLble.setBounds(0,0,300,300);
            gchjFrame.getContentPane().add(gongchonghaojLble);
            gchjFrame.setVisible(true);
        }else if(source == item5){//com/hlj/pingtu/img/dpr/split_1.png
            count = 0;
            renwu ="dpr/split_";//cr1/split_
            wurenyuantu = "dpr/IMG_20250409_233145.jpg";//com/hlj/pingtu/img/dpr/IMG_20250409_233145.jpg
            initdata();
            initImage();
        }else if(source == item6){//com/hlj/pingtu/img/dpr/split_1.png
            count=0;
            renwu ="cr1/split_";//cr1/split_
            wurenyuantu = "cr1/IMG_20250408_194942.jpg";
            initdata();
            initImage();
        }
    }
}