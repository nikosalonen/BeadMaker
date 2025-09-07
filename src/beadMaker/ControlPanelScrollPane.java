package beadMaker;

import core.swingComponent.BorderMaker;
import javax.swing.JScrollPane;

public class ControlPanelScrollPane extends JScrollPane {

  public ControlPanelScrollPane(ControlPanel controlPanel) {
    super(controlPanel);
    this.setBorder(new BorderMaker(BorderMaker.RAISEDBEVEL, 4, 8));
    this.getVerticalScrollBar().setUnitIncrement(16);
  }
}
