package methods;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.layout.BorderPane;

public class DataItemRemovalEvent extends Event{
    public static final EventType<DataItemRemovalEvent> REMOVE_CHILD = new EventType<>(Event.ANY, "REMOVE_CHILD");

        private final BorderPane childToRemove;
        public DataItemRemovalEvent(BorderPane childToRemove) {
            super(REMOVE_CHILD);
            this.childToRemove = childToRemove;
        }
        public BorderPane getChildToRemove() {
            return childToRemove;
        }
}
