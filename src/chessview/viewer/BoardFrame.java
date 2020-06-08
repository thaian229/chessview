package chessview.viewer;

import chessview.Board;
import chessview.ChessGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * <h1>Class BoardFrame</h1>
 * This class create frame board which has buttons to show steps of the game
 */

public class BoardFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel bottomInnerPanel;
	private JPanel leftInnerPanel;
	private JPanel rightInnerPanel;
	private BoardCanvas boardCanvas;
	private RoundCanvas roundCanvas;

	/**
	 * Construct board frame
	 * @param game - a given game
	 */
	public BoardFrame(ChessGame game) {
		super("Chess View");

		List<Board> boards = game.boards();
		boardCanvas = new BoardCanvas(boards);
		roundCanvas = new RoundCanvas(game.rounds(),boards);
		
		leftInnerPanel = new JPanel();
		leftInnerPanel.setLayout(new BorderLayout());
		Border cb = BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(3, 3, 3, 3), BorderFactory
				.createLineBorder(Color.gray));
		leftInnerPanel.setBorder(cb);
		leftInnerPanel.add(boardCanvas, BorderLayout.CENTER);
		
		rightInnerPanel = new JPanel();
		rightInnerPanel.setLayout(new BorderLayout());
		cb = BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(3, 3, 3, 3), BorderFactory
				.createLineBorder(Color.gray));
		rightInnerPanel.setBorder(cb);		
		rightInnerPanel.add(roundCanvas, BorderLayout.CENTER);
	
		JButton endbk = new JButton("|<<");
		JButton bkbk = new JButton("<<");
		JButton bk = new JButton("<");
		JButton fdend = new JButton(">>|");
		JButton fdfd = new JButton(">>");
		JButton fd = new JButton(">");
		
		endbk.addActionListener(this);
		bkbk.addActionListener(this);
		bk.addActionListener(this);
		fdend.addActionListener(this);
		fdfd.addActionListener(this);
		fd.addActionListener(this);		
		
		bottomInnerPanel = new JPanel();
		bottomInnerPanel.add(endbk);
		bottomInnerPanel.add(bkbk);
		bottomInnerPanel.add(bk);
		bottomInnerPanel.add(fd);
		bottomInnerPanel.add(fdfd);
		bottomInnerPanel.add(fdend);
		
		add(leftInnerPanel,BorderLayout.CENTER);
		add(rightInnerPanel,BorderLayout.EAST);
		add(bottomInnerPanel,BorderLayout.SOUTH);
		
		setFocusable(true);
		addKeyListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		pack();
		setVisible(true);
	}

	/**
	 * Construct action when user click any button
	 * @param e - event
	 */
	public void actionPerformed(ActionEvent e) {
		// When a toolbar button or menu item is
		// clicked on this function will be called
		String cmd = e.getActionCommand();
		if (cmd.equals(">")) {
			boardCanvas.fwd(1);
			roundCanvas.fwd(1);	
		} else if (cmd.equals(">>")) {
			boardCanvas.fwd(5);						
			roundCanvas.fwd(5);
		} else if (cmd.equals(">>|")) {
			boardCanvas.fwd(90000);						
			roundCanvas.fwd(90000);
		} else if (cmd.equals("<")) {
			boardCanvas.bwd(1);						
			roundCanvas.bwd(1);
		} else if (cmd.equals("<<")) {
			boardCanvas.bwd(5);						
			roundCanvas.bwd(5);
		} else if (cmd.equals("|<<")) {
			boardCanvas.bwd(90000);						
			roundCanvas.bwd(90000);
		}
	}
	
	// METHODS REQUIRED FOR KEY LISTENER

	/**
	 * Notice when user pressed a key
	 * @param e - event
	 */
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT) {
			boardCanvas.fwd(1);
			roundCanvas.fwd(1);
		} else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT) {
			boardCanvas.bwd(1);
			roundCanvas.bwd(1);
		} else if(code == KeyEvent.VK_UP) {
			boardCanvas.fwd(90000);
			roundCanvas.fwd(90000);
		} else if(code == KeyEvent.VK_DOWN) {
			boardCanvas.bwd(90000);
			roundCanvas.bwd(90000);
		}
	}

	/**
	 * Notice when user released a key
	 * @param e - event
	 */
	public void keyReleased(KeyEvent e) {		
	}

	/**
	 * Notice when user typed a character
	 * @param e - event
	 */
	public void keyTyped(KeyEvent e) {
		
	}
}
