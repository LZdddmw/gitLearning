package livegame;

import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		UI ui = new UI();
		ui.playUI();//����UI����
		
		ChessBoard chessBoard = new ChessBoard();//����һ�����̣�������Ϊinitshape ��time��ui�������lz��
		InitShape.board=chessBoard;//�����̶��󴫵���ʼ����״��״̬����(����ֵ��ʹ����������ָ��ͬһ������lz��
		InitState.ui=ui;
		
		JPanel panels[][]=ui.getJps();//���UI��ÿ���������ӣ�
		
		chessBoard.setPanels(panels);//����������������
		ui.setChessBord(chessBoard);//UI������̶���
		chessBoard.play(panels);//������������
	}

}
