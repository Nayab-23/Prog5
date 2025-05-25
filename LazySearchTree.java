package lazyTrees ;

/**
 * Implementation of a Binary Search Tree with lazy deletion.
 * @param <E> The type of elements stored in the tree, must be Comparable
 */
public class LazySearchTree<E extends Comparable<? super E>> {
    private int mSize;
    private int mSizeHard;
    private LazySTNode root;

    /**
     * Default constructor initializes an empty tree
     */
    public LazySearchTree() {
        clear();
    }

    /**
     * Clears the tree, removing all elements
     */
    public void clear() {
        mSize = 0;
        mSizeHard = 0;
        root = null;
    }

    /**
     * Returns the soft size (number of non-deleted elements)
     */
    public int size() {
        return mSize;
    }

    /**
     * Returns the hard size (total number of nodes including deleted ones)
     */
    public int sizeHard() {
        return mSizeHard;
    }

    /**
     * Checks if the tree is empty (no non-deleted elements)
     */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * Finds an object in the tree
     */
    public E find(E x) {
        LazySTNode resultNode = find(root, x);
        if (resultNode == null)
            throw new java.util.NoSuchElementException();
        return resultNode.data;
    }

    /**
     * Checks if an object exists in the tree (not marked as deleted)
     */
    public boolean contains(E x) {
        return find(root, x) != null;
    }

    /**
     * Inserts an object into the tree
     */
    public boolean insert(E x) {
        int oldSize = mSize;
        root = insert(root, x);
        return mSize != oldSize;
    }

    /**
     * Removes an object from the tree (marks it as deleted)
     */
    public boolean remove(E x) {
        int oldSize = mSize;
        root = remove(root, x);
        return mSize != oldSize;
    }

    /**
     * Finds the minimum value in the tree (not marked as deleted)
     */
    public E findMin() {
        if (empty())
            throw new java.util.NoSuchElementException();
        return findMin(root).data;
    }

    /**
     * Finds the maximum value in the tree (not marked as deleted)
     */
    public E findMax() {
        if (empty())
            throw new java.util.NoSuchElementException();
        return findMax(root).data;
    }

    /**
     * Traverses all nodes in the tree (including deleted ones)
     */
    public <F extends Traverser<? super E>> void traverseHard(F func) {
        traverseHard(root, func);
    }

    /**
     * Traverses only non-deleted nodes in the tree
     */
    public <F extends Traverser<? super E>> void traverseSoft(F func) {
        traverseSoft(root, func);
    }

    /**
     * Finds the minimum value in the tree (ignores deleted flag)
     */
    protected LazySTNode findMinHard(LazySTNode root) {
        if (root == null) return null;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Finds the maximum value in the tree (ignores deleted flag)
     */
    protected LazySTNode findMaxHard(LazySTNode root) {
        if (root == null) return null;
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Hard remove: physically removes a node from the tree (not just marking deleted)
     * Handles 0, 1, and 2 child cases. Updates mSize/mSizeHard and deleted flag.
     */
    protected LazySTNode removeHard(LazySTNode root, E x) {
        if (root == null) return null;
        int compareResult = x.compareTo(root.data);
        if (compareResult < 0) {
            root.left = removeHard(root.left, x);
        } else if (compareResult > 0) {
            root.right = removeHard(root.right, x);
        } else {
            // Found node to hard delete
            if (root.left != null && root.right != null) {
                // Two children: replace with min in right subtree
                LazySTNode minNode = findMinHard(root.right);
                root.data = minNode.data;
                root.deleted = minNode.deleted;
                root.right = removeHard(root.right, root.data);
            } else {
                // One or zero children
                LazySTNode child = (root.left != null) ? root.left : root.right;
                if (!root.deleted) mSize--; // Only decrement mSize if not already deleted
                mSizeHard--;
                return child;
            }
        }
        return root;
    }

    /**
     * Public method to collect garbage (hard delete all nodes marked as deleted)
     * Returns true if any node was hard deleted.
     */
    public boolean collectGarbage() {
        int oldSizeHard = mSizeHard;
        root = collectGarbage(root);
        return mSizeHard != oldSizeHard;
    }

    /**
     * Private recursive helper for collectGarbage (post-order traversal)
     */
    private LazySTNode collectGarbage(LazySTNode root) {
        if (root == null) return null;
        root.left = collectGarbage(root.left);
        root.right = collectGarbage(root.right);
        if (root.deleted) {
            return removeHard(root, root.data);
        }
        return root;
    }

    // Private helper methods
    private LazySTNode find(LazySTNode root, E x) {
        if (root == null)
            return null;

        int compareResult = x.compareTo(root.data);

        if (compareResult < 0)
            return find(root.left, x);
        if (compareResult > 0)
            return find(root.right, x);
        if (root.deleted)
            return null;
        return root;
    }

    private LazySTNode insert(LazySTNode root, E x) {
        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null);
        }

        int compareResult = x.compareTo(root.data);

        if (compareResult < 0)
            root.left = insert(root.left, x);
        else if (compareResult > 0)
            root.right = insert(root.right, x);
        else if (root.deleted) {
            root.deleted = false;
            mSize++;
        }

        return root;
    }

    private LazySTNode remove(LazySTNode root, E x) {
        if (root == null)
            return null;

        int compareResult = x.compareTo(root.data);

        if (compareResult < 0)
            root.left = remove(root.left, x);
        else if (compareResult > 0)
            root.right = remove(root.right, x);
        else if (!root.deleted) {
            root.deleted = true;
            mSize--;
        }

        return root;
    }

    private LazySTNode findMin(LazySTNode root) {
        if (root == null)
            return null;

        LazySTNode current = root;
        LazySTNode result = null;

        while (current != null) {
            if (!current.deleted) {
                result = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return result;
    }

    private LazySTNode findMax(LazySTNode root) {
        if (root == null)
            return null;

        LazySTNode current = root;
        LazySTNode result = null;

        while (current != null) {
            if (!current.deleted) {
                result = current;
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return result;
    }

    private <F extends Traverser<? super E>> void traverseHard(LazySTNode root, F func) {
        if (root != null) {
            traverseHard(root.left, func);
            func.visit(root.data);
            traverseHard(root.right, func);
        }
    }

    private <F extends Traverser<? super E>> void traverseSoft(LazySTNode root, F func) {
        if (root != null) {
            traverseSoft(root.left, func);
            if (!root.deleted)
                func.visit(root.data);
            traverseSoft(root.right, func);
        }
    }

    // Private nested class for tree nodes
    private class LazySTNode {
        public LazySTNode left, right;
        public E data;
        public boolean deleted;

        public LazySTNode(E data, LazySTNode left, LazySTNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.deleted = false;
        }
    }
} 