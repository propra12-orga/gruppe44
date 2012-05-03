import java.awt.*;

public class init extends Frame {
	public int size = 15;
	public int size_px;
	
	public init() {
		super("Bombergarden");
		addWindowListener(new WindowClosingAdapter(true));
		setBackground(Color.WHITE);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		DisplayMode dm = gd.getDisplayMode();
			
		size_px = (int)(dm.getHeight()-20) / size;

		setSize(dm.getWidth(),dm.getHeight());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	
	
	public void paint(Graphics g) {
		for (int i = 1; i<15; i+=2) {
			for (int j=1; j<15; j+=2) {
				g.fillRect(j * size_px, i * size_px, size_px, size_px);
			}
		}
		//g.setColor(Color.RED);
		//g.fillRect(0, 0, size_px, size_px);
	}
}