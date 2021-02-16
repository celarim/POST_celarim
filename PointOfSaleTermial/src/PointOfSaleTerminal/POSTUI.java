package PointOfSaleTerminal;

import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import javax.swing.*;
/**
 * 
 */


public class POSTUI {
	
	static String bt = "상품명\t\t상품번호\t\t정가\t\t구매가격";
    /**
     * Default constructor
     */
    public POSTUI() {
    	post = new POST();
    }
    
    /**
     * 프레임
     */
    private JFrame frame;

    /**
     * main panel의 입력칸
     */
    private JTextField text;
    
    /**
     * main panel의 현재 cart의 내용을 보여주는 칸
     */    
    private JTextArea context;
    
    /**
     * 여러 패널들
     * mainpn - 메인패널
     * dispn - 할인/제거 패널
     * refundpn - 환불 패널
     */
    private JPanel mainpn;
    private JPanel dispn;
    private JPanel refundpn;
    
    /**
     * 여러 버튼들
     * dis_button - 할인/제거 버튼
     * refund_button - 환불 버튼
     * pay_button - 결제 버튼
     * sold_button - 매출 버튼
     */
    private JButton dis_button;
    private JButton refund_button;
    private JButton pay_button;
    private JButton sold_button;
    
    /**
     * 사용할 post
     */
    private POST post;

    /**
     * main panel을 구성하는 함수
     */
    public void CreateMainWidget() {
        // TODO implement here
    	this.frame = new JFrame("JUn");
    	mainpn = new JPanel();
    	
    	this.text = new JTextField();
    	GridBagConstraints textgbc = new GridBagConstraints();
    	
    	this.context = new JTextArea();
    	context.setEditable(false);
    	context.setText(bt);
    	GridBagConstraints Cgbc = new GridBagConstraints();
    	
    	this.dis_button = new JButton("할인/제거");
    	this.refund_button = new JButton("환불");
    	this.pay_button = new JButton("결제");
    	this.sold_button = new JButton("매출");
    	
    	GridBagConstraints disgbc = new GridBagConstraints();
    	GridBagConstraints refundgbc = new GridBagConstraints();
    	GridBagConstraints paygbc = new GridBagConstraints();
    	GridBagConstraints soldgbc = new GridBagConstraints();
    	
    	
    	/*JButton quit_button = new JButton("quit");
    	this.frame.add(quit_button);
    	quit_button.addActionListener(listener);*/
    	
    	GridBagLayout gbl = new GridBagLayout();
    	mainpn.setLayout(gbl);
    	
    	textgbc.gridx = 0;
    	textgbc.gridy = 0;
    	textgbc.gridwidth = 4;
    	textgbc.weightx = 1;
    	textgbc.weighty = 1;
    	textgbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(text, textgbc);
    	
    	Cgbc.gridx = 0;
    	Cgbc.gridy = 1;
    	Cgbc.gridwidth = 4;
    	Cgbc.weightx = 1;
    	Cgbc.weighty = 6;
    	Cgbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(new JScrollPane(context), Cgbc);
    	
    	disgbc.gridx = 0;
    	disgbc.gridy = 6;
    	disgbc.weightx = 1;
    	disgbc.weighty = 2;
    	disgbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(dis_button,disgbc);
    	refundgbc.gridx = 1;
    	refundgbc.gridy = 6;
    	refundgbc.weightx = 1;
    	refundgbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(refund_button,refundgbc);
    	paygbc.gridx = 2;
    	paygbc.gridy = 6;
    	paygbc.weightx = 1;
    	paygbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(pay_button,paygbc);
    	soldgbc.gridx = 3;
    	soldgbc.gridy = 6;
    	soldgbc.weightx = 1;
    	soldgbc.fill = GridBagConstraints.BOTH;
    	mainpn.add(sold_button,soldgbc);
    }
    
    /**
     * 할인/제거 버튼을 누르면 나오는 패널을 구성한 함수
     */
    private void CreateDisWidget() {
    	this.dispn = new JPanel();
    	GridBagLayout disgbl = new GridBagLayout();
    	dispn.setLayout(disgbl);
    	
    	JRadioButton radio[] = new JRadioButton[post.getCart().size()];
    	GridBagConstraints rgbc[] = new GridBagConstraints[post.getCart().size()];
    	ButtonGroup g = new ButtonGroup();
    	for(int i=0; i<radio.length; i++) {
    		rgbc[i] = new GridBagConstraints();
    		radio[i] = new JRadioButton(post.getCart().get(i));
    		radio[i].setActionCommand(Integer.toString(i));
    		rgbc[i].gridx = 0;
    		rgbc[i].gridy = i;
    		rgbc[i].weightx = 1;
    		rgbc[i].fill = GridBagConstraints.BOTH;
    		g.add(radio[i]);
    		dispn.add(radio[i],rgbc[i]);
    	}

    	JTextField dtext = new JTextField();
    	
    	JButton can_button = new JButton("취소");
    	can_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mainpn);
				frame.revalidate();
				frame.repaint();
			}
    		
    	});
    	GridBagConstraints cangbc = new GridBagConstraints();
    	cangbc.gridx = 1;
    	cangbc.gridy = post.getCart().size();
    	
    	
    	JButton discount_button = new JButton("할인");
    	discount_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = Integer.parseInt(g.getSelection().getActionCommand());
				int how = Integer.parseInt(dtext.getText());
				post.Discount(index, how);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mainpn);
				frame.revalidate();
				frame.repaint();
				update();
				
			}
    		
    	});
    	GridBagConstraints discountgbc = new GridBagConstraints();
    	discountgbc.gridx = 2;
    	discountgbc.gridy = post.getCart().size();
    	
    	JButton discard_button = new JButton("제거");
    	discard_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = Integer.parseInt(g.getSelection().getActionCommand());
				post.DeleteCart(index);;
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mainpn);
				frame.revalidate();
				frame.repaint();
				update();
			}
    		
    	});
    	GridBagConstraints discardgbc = new GridBagConstraints();
    	discardgbc.gridx = 3;
    	discardgbc.gridy = post.getCart().size();


    	GridBagConstraints dtextgbc = new GridBagConstraints();
    	dtextgbc.gridx = 0;
    	dtextgbc.gridy = post.getCart().size();
    	dtextgbc.fill = GridBagConstraints.BOTH;
    	
    	dispn.add(can_button, cangbc);
    	dispn.add(discount_button, discountgbc);
    	dispn.add(dtext, dtextgbc);
    	dispn.add(discard_button, discardgbc);
    	frame.add(dispn);
    	
    }
    
    /**
     * 
     */
    private void CreateRefundWidget() {
    	this.refundpn = new JPanel();
    	JTextField date = new JTextField();
    	GridBagConstraints dgbc = new GridBagConstraints();
    	dgbc.gridx = 0;
    	dgbc.gridy = 0;
    	dgbc.gridwidth = 2;
    	dgbc.fill = GridBagConstraints.BOTH;
    	
    	JLabel lab = new JLabel("텍스트");
    	GridBagConstraints labgbc = new GridBagConstraints();
    	labgbc.gridx = 0;
    	labgbc.gridy = 2;
    	labgbc.gridwidth = 2;
    	
    	JButton can_button = new JButton("취소");
    	GridBagConstraints cgbc = new GridBagConstraints();
    	cgbc.gridx = 0;
    	cgbc.gridy = 1;
    	
    	JButton refund_button = new JButton("환불");
    	GridBagConstraints refundgbc = new GridBagConstraints();
    	refundgbc.gridx = 1;
    	refundgbc.gridy = 1;
    	
    	GridBagLayout gbl= new GridBagLayout();
    	refundpn.setLayout(gbl);
    	
    	refundpn.add(date, dgbc);
    	refundpn.add(lab, labgbc);
    	refundpn.add(can_button,cgbc);
    	refundpn.add(refund_button, refundgbc);
    	frame.add(refundpn);
    	
    	
    	date.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					if (post.StartRefund(Integer.parseInt(date.getText()))) {
						lab.setText("판매내역이 존재합니다. 환불하시겠습니까?");
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    	can_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mainpn);
				frame.revalidate();
				frame.repaint();
			}
    		
    	});
    	
    	refund_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				post.Repurchase();
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mainpn);
				frame.revalidate();
				frame.repaint();
			}
    		
    	});
    }
    
    /**
     * main panel의 context를 현재 Cart에 맞게 바꾸어주는 함수
     */
    private void update() {
		text.setText("");
		context.setText(bt);
		for(String str:post.getCart()) {
			context.setText(context.getText().concat("\n").concat(str));
		}
    }
    
    
    /**
     * main panel에 있는 위젯들의 활동을 정하는 함수
     */
    private void CreateMethod() {
    	this.text.addKeyListener(new KeyListener() {

    		@Override
    		public void keyTyped(KeyEvent e) {
    			// TODO Auto-generated method stub
    			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
    				post.AddCart(text.getText().strip());
    				update();
    			}
    		}

    		@Override
    		public void keyPressed(KeyEvent e) {
    			// TODO Auto-generated method stub
    			
    		}

    		@Override
    		public void keyReleased(KeyEvent e) {
    			// TODO Auto-generated method stub
    			
    		}
        	
        });
    	this.pay_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (post.Purchase()) {
					context.setText(bt);
				}
			}
    		
    	});
    	this.dis_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateDisWidget();
				frame.getContentPane().removeAll();
				frame.getContentPane().add(dispn);
				frame.revalidate();
				frame.repaint();
				
			}
    		
    	});
    	this.refund_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreateRefundWidget();
				frame.getContentPane().removeAll();
				frame.getContentPane().add(refundpn);
				frame.revalidate();
				frame.repaint();
				
			}
    		
    	});
    	this.frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
    	
    }
    
    /**
     * frame의 기본 화면 구성
     */
    public void run() {
    	this.CreateMainWidget();
    	this.CreateMethod();
    	this.frame.add(mainpn);
    	this.frame.setSize(600,300);
    	this.frame.setVisible(true);
    }
    
    public static void main(String[] args) {
    	POSTUI p = new POSTUI();
    	p.run();
    }
}


