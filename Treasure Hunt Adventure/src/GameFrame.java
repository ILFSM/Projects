/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package treasurehuntadventuregame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Yakup
 */
public class BozdemirMavlyudovGameFrame extends javax.swing.JFrame {

    /**
     * Creates new form BozdemirMavlyudovGameFrame
     */
    class Player {

        String username;
        int score;
        int level;
        int step = 0;

        public Player() {
            this.score = 0;
            this.level = 1;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void increaseScore(int score) {
            this.score += score;
        }

        public void decreaseScore(int score) {
            this.score -= score;
        }
    }
    /**
     * Creates new form Game
     */
    Player p1 = new Player();
    private Timer timer;
    private int animationCount = 0;
    private Random random = new Random();
    BozdemirMavlyudovLinkedList<Integer> list = new BozdemirMavlyudovLinkedList();

    private ImageIcon getDiceIcon(int diceNumber) {
        return new ImageIcon(getClass().getResource("/images/dice" + diceNumber + ".png"));
    }

    public BozdemirMavlyudovGameFrame() {
        initComponents();

        setLocationRelativeTo(null);

        int[] values = new int[30];
        int index = 0;

        for (int i = 0; i < 10; i++) {
            values[index++] = 0;
        }
        for (int i = 0; i < 10; i++) {
            values[index++] = 10;
        }
        for (int i = 0; i < 10; i++) {
            values[index++] = -5;
        }

        Random rand = new Random();
        for (int i = values.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }

        for (int value : values) {
            list.add(value);
        }

        jButton4.setOpaque(true);
        jButton4.setContentAreaFilled(true);
        jButton4.setBorderPainted(true);
        jButton4.setFocusPainted(false);
        jButton4.setFont(new Font("SansSerif", Font.BOLD, 18));
        jButton4.setForeground(Color.WHITE);
        jButton4.setBackground(new Color(0, 123, 255));
        jButton4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        list.printall();

        jButton1.setOpaque(
                false);
        jButton1.setContentAreaFilled(
                false);
        jButton1.setBorderPainted(
                false);
        jButton1.setFocusPainted(
                false);

        jTextField1.setOpaque(
                false);
        jTextField1.setBorder(
                null);
        jTextField1.setBackground(
                new Color(0, 0, 0, 0));
        jTextField1.setForeground(Color.BLACK);

        jTextField1.setCaretColor(Color.BLACK);

        jTextField1.setFont(
                new Font("Arial", Font.PLAIN, 25));

        jButton2.setOpaque(
                false);
        jButton2.setContentAreaFilled(
                false);
        jButton2.setBorderPainted(
                false);
        jButton2.setFocusPainted(
                false);

        jButton3.setOpaque(
                false);
        jButton3.setContentAreaFilled(
                false);
        jButton3.setBorderPainted(
                false);
        jButton3.setFocusPainted(
                false);
        
        jTextArea2.setVisible(false);
        jTextArea2.setOpaque(false);
        jTextArea2.setForeground(Color.WHITE);
        jTextArea2.setFont(new Font("Arial", Font.BOLD, 16));
        jTextArea2.setBackground(new Color(0, 0, 0, 0));
        jTextArea2.setBorder(null);
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setBorder(null);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append("[").append(i).append("] ").append(list.get(i)).append("\n");
        }
        jTextArea1.setText(sb.toString());

        jTextArea1.setOpaque(false);
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setFont(new Font("Arial", Font.BOLD, 16));
        jTextArea1.setBackground(new Color(0, 0, 0, 0));
         jTextArea1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setBorder(null);

        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 323, 330, 60));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 580, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background1.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, 340));

        jScrollPane2.setBorder(null);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTextArea2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 210, 220));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Score : ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 131, 56));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Level :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 130, 60));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("0");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 70, 56));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setText("1");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 80, 51));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dice1.png"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 160, 150));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("Step :  ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 100, 60));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel10.setText("0");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 120, 60));

        jButton4.setText("Roll");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/map.jpg"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 610));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 210, 70));

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 210, 70));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background3.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        p1.username = jTextField1.getText();
        p1.level = 1;
        p1.score = 0;
        jPanel1.setVisible(false);
        jPanel3.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            FileWriter writer = new FileWriter("score.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String levelStr = "level" + p1.level;
            String line = p1.username + ", " + levelStr + ", " + p1.score;

            bufferedWriter.write(line);
            bufferedWriter.newLine();

            bufferedWriter.close();
            System.out.println("Score successfully saved to file: " + line);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        p1.level = 1;
        p1.score = 0;

        BozdemirMavlyudovMainFrame menu = new BozdemirMavlyudovMainFrame();
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (p1.level == 1 && p1.step >= 30) {
            try {
                FileWriter writer = new FileWriter("score.txt", true);
                writer.write(p1.username + ", level1, " + p1.score + "\n");
                writer.close();
                JOptionPane.showMessageDialog(null, "Level 1 completed!\nScore: "
                        + p1.score,
                        "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                System.out.println("File write error: " + ex.getMessage());
            }
        }

        p1.level = 2;
        p1.step = 0;
        jLabel5.setText("2");

        list.header = null;
        list.tail = null;

        int[] values = new int[30];
        int index = 0;

        for (int i = 0; i < 10; i++) {
            values[index++] = 0;
        }
        for (int i = 0; i < 10; i++) {
            values[index++] = 10;
        }
        for (int i = 0; i < 10; i++) {
            values[index++] = -5;
        }

        Random rand = new Random();
        for (int i = values.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }

        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }

        Dnode<Integer>[] nodeArray = new Dnode[30];
        Dnode<Integer> temp = list.header;
        int i = 0;
        while (temp != null && i < 30) {
            nodeArray[i] = temp;
            temp = temp.next;
            i++;
        }

        int[] usedIndexes = new int[5];
        int count = 0;
        while (count < 5) {
            int randIndex = rand.nextInt(28) + 1;
            boolean used = false;
            for (int k = 0; k < count; k++) {
                if (usedIndexes[k] == randIndex) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                usedIndexes[count++] = randIndex;
            }
        }

        for (int j = 0; j < 5; j++) {
            int fromIndex = usedIndexes[j];
            int toIndex;
            do {
                toIndex = rand.nextInt(30);
            } while (toIndex == fromIndex);
            nodeArray[fromIndex].jump = nodeArray[toIndex];
        }

        System.out.println("\nJump Connections:");
        for (int j = 0; j < 5; j++) {
            int from = usedIndexes[j];
            int to = -1;
            for (int k = 0; k < 30; k++) {
                if (nodeArray[from].jump == nodeArray[k]) {
                    to = k;
                    break;
                }
            }
            System.out.println("Island " + (from + 1) + " jumps to Island " + (to + 1));
        }

        System.out.println("\nAll islands:");
        Dnode<Integer> curr = list.header;
        int counter = 1;
        while (curr != null) {
            System.out.print("Island " + counter + " [" + curr.data + "]");
            if (curr.jump != null) {
                for (int x = 0; x < 30; x++) {
                    if (nodeArray[x] == curr.jump) {
                        System.out.print(" -> Jumps to Island " + (x + 1));
                        break;
                    }
                }
            }
            System.out.println();
            curr = curr.next;
            counter++;
        }
        System.out.println("-----------------------");

        jTextArea2.setVisible(true);
        StringBuilder jumpInfo = new StringBuilder("Jump Connections:\n");
        for (int j = 0; j < 5; j++) {
            int from = usedIndexes[j];
            int to = -1;
            for (int k = 0; k < 30; k++) {
                if (nodeArray[from].jump == nodeArray[k]) {
                    to = k;
                    break;
                }
            }
            jumpInfo.append("Island ").append(from + 1).append(" jumps to Island ").append(to + 1).append("\n");
        }
        jTextArea2.setText(jumpInfo.toString());

        jPanel1.setVisible(false);
        jPanel3.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Timer timer = new Timer(100, null);
        Random random = new Random();
        final int[] count = {0};
        final int[] finalDiceValue = {0};

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceNumber = random.nextInt(6) + 1;
                jLabel8.setIcon(getDiceIcon(diceNumber));
                count[0]++;

                if (count[0] >= 10) {
                    ((Timer) e.getSource()).stop();

                    finalDiceValue[0] = random.nextInt(6) + 1;
                    jLabel8.setIcon(getDiceIcon(finalDiceValue[0]));

                    p1.step += finalDiceValue[0];
                    System.out.println("Dice result: "
                            + finalDiceValue[0]);
                    System.out.println("New step: "
                            + p1.step);

                    if (p1.level == 2) {
                        Dnode<Integer> node = list.header;
                        for (int i = 0; i < p1.step - 1 && node != null; i++) {
                            node = node.next;
                        }

                        if (node != null) {
                            p1.score += node.data;
                            System.out.println("Score added: "
                                    + node.data);

                            if (node.jump != null) {
                                Dnode<Integer> target = node.jump;

                                int index = 0;
                                Dnode<Integer> temp = list.header;
                                while (temp != null && temp != target) {
                                    temp = temp.next;
                                    index++;
                                }

                                if (temp == target) {
                                    p1.step = index + 1;
                                    System.out.println("Jump executed! New step: " + p1.step);
                                    p1.score += target.data;
                                    System.out.println("Score added after jump: " + target.data);
                                }
                            }
                        }

                        if (p1.step >= 30) {
                            try {
                                FileWriter writer = new FileWriter("score.txt", true);
                                writer.write(p1.username + ", level2, " + p1.score + "\n");
                                writer.close();
                            } catch (IOException ex) {
                                System.out.println("File write error: " + ex.getMessage());
                            }

                            JOptionPane.showMessageDialog(null, "Congratulations! Level 2 completed.\nYour score: " + p1.score);

                            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(jPanel2);
                            currentFrame.dispose();

                            BozdemirMavlyudovMainFrame menu = new BozdemirMavlyudovMainFrame();
                            menu.setVisible(true);
                            return;
                        }

                    } else {
                        if (p1.step >= 30) {
                            jPanel1.setVisible(false);
                            jPanel2.setVisible(false);
                            jPanel3.setVisible(true);
                            return;
                        }

                        Dnode<Integer> node = list.header;
                        for (int i = 0; i < p1.step - 1 && node != null; i++) {
                            node = node.next;
                        }

                        if (node != null) {
                            p1.score += node.data;
                            System.out.println("Score added: " + node.data);
                        }
                    }

                    jLabel4.setText(p1.score + "");
                    jLabel10.setText("" + p1.step);
                    System.out.println("Toplam skor: " + p1.score);
                }
            }
        });
        System.out.println("-----------------");
        timer.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BozdemirMavlyudovGameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BozdemirMavlyudovGameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BozdemirMavlyudovGameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BozdemirMavlyudovGameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BozdemirMavlyudovGameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
