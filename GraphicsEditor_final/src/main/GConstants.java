package main;


import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import menus.GColorMenu;
import menus.GEditMenu;
import menus.GFileMenu;
import menus.GMenu;
import shape.GSketch;
import shape.GArc;
import shape.GGroup;
import shape.GLine;
import shape.GOval;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;


public class GConstants {

	public static final long serialVersionUID = 1L;
	
	public GConstants() {
	}
	
	public enum EMainFrame {
		eWidth(1550),
		eHeight(830);
		
		private int value;		
		private EMainFrame(int value) {
			this.value = value;
		}	
		public int getValue() {
			return this.value;
		}
	}
	
	public enum EMenubar {
		eFile(new GFileMenu("����")),
		eEdit(new GEditMenu("����")),
		eColor(new GColorMenu("�÷� & ��"));
		
		private GMenu menu;
		private EMenubar(GMenu menu) {
			this.menu = menu;
		}		
		public GMenu getMenu() {
			return this.menu;
		}
	}
	
	public enum EFileMenu {
		eNew("New","nnew",KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)),
		eImageOpen("�̹��� ����","imageOpen",KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK)),
		eOpen("����","open",KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK)),
		eSave("����","save",KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)),
		eSaveAs("�ٸ��̸�����","saveAs",KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK)),
		ePrint("����Ʈ","print",KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK)),
		eQuit("����","exit",KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		
		private String title;	
		private String actionCommand;
		private KeyStroke command;
		private EFileMenu(String title,String actionCommand, KeyStroke command) {
			this.title = title;
			this.actionCommand = actionCommand;
			this.command = command;
		}		
		public String getTitle() {
			return this.title;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
		public KeyStroke getCommand() {
			return this.command;
		}
		
	}
	
	public enum EEditMenu{
		eUndo("�ǵ�����","unde",KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK)),
		eRedo("�ٽý���","redo",KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)),
		eCopy("����","copy",KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK)),
		eCut("�ڸ���","cut",KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK)),
		ePaste("�ٿ��ֱ�","paste",KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK)),
		eDelete("�����", "delete",KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK)),
		eGroup("�׷�","group",KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK)),
		eUnGroup("�׷� ����","unGroup",KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		
		private String title;	
		private String actionCommand;
		private KeyStroke command;
		private EEditMenu(String title,String actionCommand, KeyStroke command) {
			this.title = title;
			this.actionCommand = actionCommand;
			this.command = command;
		}		
		public String getTitle() {
			return this.title;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
		public KeyStroke getCommand() {
			return this.command;
		}
	}
	
	public enum EColorMenu {
		eLineColor("���� ��","setLineColor",KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)),
		eFillColor("ä��� ��","setFillColor",KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK)),
		eStroke("�� ����", "setStroke", KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		
		private String title;	
		private String actionCommand;
		private KeyStroke command;
		private EColorMenu(String title,String actionCommand, KeyStroke command) {
			this.title = title;
			this.actionCommand = actionCommand;
			this.command = command;
		}		
		public String getTitle() {
			return this.title;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
		public KeyStroke getCommand() {
			return this.command;
		}
	}	
	
	public enum EToolbar {
		eRectangle("data/rectangle1.png","data/rectangle2.png", new GRectangle()),
		eOval("data/oval1.png","data/oval2.png", new GOval()),
		eLine("data/line1.png","data/line2.png", new GLine()),
		ePolygon("data/polygon1.png","data/polygon2.png", new GPolygon()),
		eSelect("data/select1.png","data/select2.png", new GGroup()),
		eSketch("data/pen1.jpg","data/pen2.PNG", new GSketch()),
		eArc("data/arc1.png","data/arc2.png", new GArc());
		
		private String title;
		private String icontitle;
		private GShape tool;
		
		private EToolbar(String title, String icontitle, GShape tool) {
			this.title = title;
			this.icontitle = icontitle;
			this.tool = tool;
		}		
		public String getTitle() {
			return this.title;
		}
		public String getIconTitle() {
			return this.icontitle;
		}
		public GShape getTool() {
			return this.tool;
		}
	}
	
	public final static int MAXPOINTS = 100;
	
	public enum ECursor {
		eDefault(new Cursor(Cursor.DEFAULT_CURSOR)),
		eMove(new Cursor(Cursor.MOVE_CURSOR)),
		eRotate(new Cursor(Cursor.HAND_CURSOR)), // �߰�
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),		
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR));
		
		private Cursor cursor;
		
		private ECursor(Cursor cursor) {
			this.cursor = cursor;
		}		
		public Cursor getCursor() {
			return this.cursor;
		}
	}
}
