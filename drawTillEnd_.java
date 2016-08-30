package HomeMade.Tools;
import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import ij.plugin.frame.*;

public class drawTillEnd_ implements PlugIn {
	ImagePlus imp;
	int[] Dim = new int[5];
	int Ch,Sl,Fr;
	int cCh,cSl,cFr;
	int[] Pos=new int[3];

	public void run(String arg){
	}
	
	public drawTillEnd_(ImagePlus im){
		imp=im;
		Dim=imp.getDimensions();
		Ch=Dim[2];
		Sl=Dim[3];
		Fr=Dim[4];
	}

	public drawTillEnd_(){
		imp = IJ.getImage();
		Dim=imp.getDimensions();
		Ch=Dim[2];
		Sl=Dim[3];
		Fr=Dim[4];
		GenericDialog gd = new GenericDialog("Draw Till End");
		String [] Choices={"Channels","Slices","Frames"};
		gd.addChoice("Draw on subsequent:",Choices,Choices[2]);
		gd.showDialog();
		String Chosen=gd.getNextChoice();
		if (Chosen=="Channels"){
			Draw_Channels();
		} else if (Chosen=="Slices"){
			Draw_Slices();
		} else {	
			Draw_Frames();
		}
	}

	public void Draw_Channels() {
		int temp= imp.getCurrentSlice();
		Pos=imp.convertIndexToPosition(temp);
		for (int t=Pos[0];t<=Ch;t++){
			imp.setPosition(t,Pos[1],Pos[2]);
			IJ.run(imp, "Draw", "slice");
		}	
	}

	public void Draw_Frames() {
		int temp= imp.getCurrentSlice();
		Pos=imp.convertIndexToPosition(temp);
		for (int t=Pos[2];t<=Fr;t++){
			imp.setPosition(Pos[0],Pos[1],t);
			IJ.run(imp, "Draw", "slice");
		}	
	}

	public void Draw_Slices() {
		int temp= imp.getCurrentSlice();
		Pos=imp.convertIndexToPosition(temp);
		for (int t=Pos[1];t<=Sl;t++){
			imp.setPosition(Pos[0],t,Pos[2]);
			IJ.run(imp, "Draw", "slice");
		}	
	}
}
