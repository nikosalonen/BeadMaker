package beadMaker;

import core.swingComponent.BorderMaker;
import javax.swing.JScrollPane;

public class PalletteScrollPane extends JScrollPane {

  public PalletteScrollPane(PallettePanel pallettePanel) {
    super(pallettePanel);
    this.setBorder(new BorderMaker(BorderMaker.RAISEDBEVEL, 4, 0));
    this.getVerticalScrollBar().setUnitIncrement(16);
  }
}
