package binfa;

public class BinfaNode {
	private char data;
	private BinfaNode left_child_charptr, right_child_charptr;

	public BinfaNode(char init_char) {
		data = init_char;
		left_child_charptr = right_child_charptr = null;
	}
	public BinfaNode() {
		data ='/';
		left_child_charptr = right_child_charptr = null;
	}
	

	public char get_data() {
		return data;
	}

	public BinfaNode get_left_child() {
		return left_child_charptr;
	}

	public BinfaNode get_right_child() {
		return right_child_charptr;
	}

	public void set_left_child(BinfaNode node) {
		left_child_charptr = node;
	}

	public void set_right_child(BinfaNode node) {
		right_child_charptr = node;
	}

	public Boolean HasChild() {
		return (right_child_charptr != null) || (left_child_charptr != null);
	}

};
