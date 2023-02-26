
/*
 *一个简单的爬虫程序，支持一次爬取一个网址
 * 输出所有html内容，并进行一定的转换，同时用正则表达式匹配超链接（更多的http网址）并输出，供进一步查找
*/
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.*;
import java.nio.charset.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.JDialog;

public class URLCrawler extends JFrame implements ActionListener {
   public static ConcurrentLinkedQueue<String> urls = new ConcurrentLinkedQueue<>();

   JLabel url = new JLabel("输入网址", JLabel.CENTER);
   JButton crawler = new JButton("给爷爬！");
   JTextField urltable = new JTextField("https://www.baidu.com");
   JTextArea resultArea = new JTextArea();
   JTextArea moreUrlArea = new JTextArea();
   JScrollPane resultTable = new JScrollPane(resultArea);
   JScrollPane moreUrlTable = new JScrollPane(moreUrlArea);
   JLabel urlCrawler = new JLabel("网页爬虫", JLabel.CENTER);
   JTextField tableCrawler = new JTextField();
   MyProgressBar mpb = null; // 进度条

   public URLCrawler() {
      super("网页爬虫");
      urltable.setHorizontalAlignment(JTextField.CENTER);
      resultArea.setLineWrap(true);
      moreUrlArea.setLineWrap(true);
      JPanel ChooseCod = new JPanel(new GridLayout());
      ChooseCod.add(urlCrawler);
      ChooseCod.add(tableCrawler);
      JPanel mainChanel = new JPanel(new GridLayout());
      mainChanel.add(BorderLayout.WEST, url);
      mainChanel.add(BorderLayout.CENTER, urltable);
      mainChanel.add(BorderLayout.EAST, crawler);
      getContentPane().setLayout(null);
      this.add(resultTable);
      this.add(ChooseCod);
      this.add(mainChanel);
      this.add(moreUrlTable);
      // 使用绝对位置，便于手操爬虫
      resultTable.setBounds(0, 50, 400, 250);
      ChooseCod.setBounds(0, 300, 600, 50);
      mainChanel.setBounds(0, 0, 600, 50);
      moreUrlTable.setBounds(400, 50, 200, 250);
      crawler.addActionListener(this);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setSize(600, 400);
      setVisible(true);

   }

   // 相应方法
   @Override
   public void actionPerformed(ActionEvent e) {
      Object source = e.getSource();
      if (source == crawler) {
         try {

            handlecrawler();

         } catch (Exception exception) {
            exception.printStackTrace();
         }
      } else
         System.out.println("Error");
   }

   public void handlecrawler() throws Exception {

      urls.add(urltable.getText());
      if (!urls.isEmpty()) {
         mpb = new MyProgressBar(this, "爬取中");
         String url = urls.poll();
         System.out.println("URL:->:" + url);
         tableCrawler.setText(String.valueOf(url));
         // 加入进度条
         mpb.setText("爬取" + url + "中..."); // 设置进度条界面标题
         mpb.setVisible(true); // 显示进度条
         String content = download(new URL(url), "utf-8");
         mpb.dispose(); // 关闭进

         if (content.equals("false")) {
            System.out.println("警告：非网页");
            resultArea.setText("警告：非网页");
         } else {
            resultArea.setText(content);
            List<String> Url_more = parse(content);
            urls.addAll(Url_more);
            moreUrlArea.setText(urls.toString());
         }

      }
      System.out.println(urls);

   }

   // 正则表达式提取超链接，并进入到队列，可以继续点击crawler进一步爬出来
   static List<String> parse(String text) {
      String patternString = "\\s*href\\s*=\\s*(\"([^\"]*\")|(\'[^\']*\')|([^\'\">\\s]+))\\s*";
      Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(text);
      List<String> list = new ArrayList<>();
      while (matcher.find()) {
         String href = matcher.group(1);
         href = href.replaceAll("\'", "").replaceAll("\"", "");
         if (href.startsWith("http:"))
            list.add(href);
      }
      return list;
   }

   // 爬取方法，使用字符流
   static String download(URL url, String charset) throws Exception {
      try (InputStream input = url.openStream(); ByteArrayOutputStream result = new ByteArrayOutputStream()) {
         int length;
         byte[] data = new byte[1024];
         while ((length = input.read(data)) != -1) {
            result.write(data, 0, length);
         }
         byte[] content = result.toByteArray();
         String resultStr = new String(content, Charset.forName(charset));

         if (resultStr.contains("html")) {
            return resultStr;
         } else {
            return "false";
         }

      }
   }

   public static void main(String[] args) throws Exception {
      SwingUtilities.invokeLater(() -> {
         new URLCrawler();
      });
   }
}

// 进度条，参考博客内容写的
class MyProgressBar extends JDialog {

   private static final long serialVersionUID = 1L;
   private JPanel jPanel = new JPanel();
   private JProgressBar jpb = new JProgressBar();
   private JLabel curSpiding = new JLabel();

   public MyProgressBar(JFrame f, String title) {
      super(f, title);
      this.setLocation(f.getWidth() / 2 + (int) f.getLocation().getX() / 2 - 80,
            f.getHeight() / 2 + (int) f.getLocation().getY() / 2 - 30);
      this.setSize(400, 100);
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      jpb.setString("玩命加载中...");
      jpb.setIndeterminate(true); // 不确定模式
      jpb.setStringPainted(true);
      jpb.setBorderPainted(false);
      jpb.setForeground(Color.RED); // 进度条颜色
      jpb.setBackground(Color.WHITE); // 背景
      curSpiding.setPreferredSize(new Dimension(400, 30));

      // 界面布局
      jPanel.setLayout(new BorderLayout());
      jPanel.add(curSpiding, BorderLayout.NORTH);
      jPanel.add(jpb, BorderLayout.CENTER);
      this.add(jPanel);
   }

   // 设置当前爬取网址接口
   public void setText(String text) {
      curSpiding.setText(text);
   }
}
