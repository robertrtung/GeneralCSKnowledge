/**
 * An object of type TreeNode represents one node in a binary tree of strings.
 */
private static class TreeNode {
   String item;      // The data in this node.
   TreeNode left;    // Pointer to left subtree.
   TreeNode right;   // Pointer to right subtree.
   TreeNode(String str) {
          // Constructor.  Make a node containing str.
          // Note that left and right pointers are null.
      item = str;
   }
}  // end class TreeNode