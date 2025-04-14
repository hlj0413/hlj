package com.hlj.pingtu;

import javax.swing.*;
import java.awt.*;

public class LoginJFrame extends JFrame {
    public LoginJFrame(){
        this.setSize(300,400);//设置框大小
        this.setTitle("游戏拼图");//框标题
        this.setAlwaysOnTop(true);//框置顶
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(3);//关闭页面就结束程序
        this.setVisible(true);//显示页面
    }
}
