package binfa;

import java.io.*;

public class binfa {
	public binfa() {
		root = new BinfaNode();
		traverse_depth = 0;
		insertion_depth = 0;
		mean_sum = 0;
		external_nodes = 1;
		current_node = root;
		max_depth = 0;
	}

	double Mean() {
		return (double) mean_sum / external_nodes;
	}

	double Variance() {

		double var = 0.0;
		variance_sum = 0;

		rvar(root);

		if ((external_nodes - 1) > 0)
			var = StrictMath.sqrt(variance_sum / (external_nodes - 1));
		else
			var = StrictMath.sqrt(variance_sum);

		return var;

	}

	void rvar(BinfaNode node) {
		double avg = Mean();

		if (node != null) {
			++traverse_depth;
			rvar(node.get_right_child());
			rvar(node.get_left_child());
			--traverse_depth;
			if (!node.HasChild())
				variance_sum += ((traverse_depth - avg) * (traverse_depth - avg));

		}

	}

	BinfaNode get_root_node() {
		return root;
	}

	void Print(BinfaNode node, PrintWriter wr) {

		if (node != null) {
			++traverse_depth;
			Print(node.get_left_child(), wr);
			for (int i = 0; i < traverse_depth; ++i)
				wr.print("-");
			wr.println(node.get_data() + "(" + (traverse_depth - 1) + ")");

			Print(node.get_right_child(), wr);
			--traverse_depth;

		}

	}

	int get_max_depth() {
		return max_depth;
	}

	int get_external_nodes() {
		return external_nodes;
	}

	void addChar(char c_char) {
		BinfaNode temp_node;

		if (c_char == '0') {
			temp_node = current_node.get_left_child();
		} else {
			temp_node = current_node.get_right_child();
		}

		++insertion_depth;

		if (temp_node != null) {
			current_node = temp_node;
		} else {
			BinfaNode new_node = new BinfaNode(c_char);

			if (current_node.HasChild()) {
				external_nodes++;
				mean_sum += insertion_depth;
			} else
				++mean_sum;

			if (c_char == '0')
				current_node.set_left_child(new_node);
			else
				current_node.set_right_child(new_node);

			if (insertion_depth > max_depth)
				max_depth = insertion_depth;

			insertion_depth = 0;

			current_node = root;
		}
	}

	BinfaNode root = new BinfaNode();
	BinfaNode current_node;

	int insertion_depth;
	int traverse_depth;
	int max_depth;
	int mean_sum;
	double variance_sum;
	int external_nodes;

}
