package gui.panel;
 
import static util.GUIUtil.setColor;
import static util.GUIUtil.showPanel;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
 
import javax.swing.JLabel;
import javax.swing.JPanel;
 
import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
 
public class SpendPanel extends WorkingPanel {
    public static SpendPanel instance = new SpendPanel();
 
    JLabel lMonthSpend = new JLabel("��������");
    JLabel lTodaySpend = new JLabel("��������");
    JLabel lAvgSpendPerDay = new JLabel("�վ�����");
    JLabel lMonthLeft = new JLabel("����ʣ��");
    JLabel lDayAvgAvailable = new JLabel("�վ�����");
    JLabel lMonthLeftDay = new JLabel("������ĩ");
 
    JLabel vMonthSpend = new JLabel("");
    JLabel vTodaySpend = new JLabel("");
    JLabel vAvgSpendPerDay = new JLabel("");
    JLabel vMonthAvailable = new JLabel("");
    JLabel vDayAvgAvailable = new JLabel("");
    JLabel vMonthLeftDay = new JLabel("1");
 
    CircleProgressBar bar;
 
    public SpendPanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
 
        setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
 
        vMonthSpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
 
        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
 
    }
 
    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(east());
 
        return p;
    }
 
    private Component east() {
 
        return bar;
    }
 
    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }
 
    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));
 
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
 
        return p;
    }
 
    public static void main(String[] args) {
        showPanel(SpendPanel.instance);
    }
 
    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
         vMonthSpend.setText(spend.monthSpend);
         vTodaySpend.setText(spend.todaySpend);
         vAvgSpendPerDay.setText(spend.avgSpendPerDay);
         vMonthAvailable.setText(spend.monthAvailable);
         vDayAvgAvailable.setText(spend.dayAvgAvailable);
         vMonthLeftDay.setText(spend.monthLeftDay);
 
         bar.setProgress(spend.usagePercentage);
         if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
  
         } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
         }
         bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
         addListener();
 
    }
 
    @Override
    public void addListener() {
        // TODO Auto-generated method stub
 
    }
}