/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package OrdenarListaEnlazada;

/**
 *
 * @author Levid Abimael
 */
public class OrdenarListaEnlazada {
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Dividir la lista en dos mitades
        ListNode mid = getMiddle(head);
        ListNode nextToMid = mid.next;
        mid.next = null;
        
        // Ordenar ambas mitades
        ListNode left = sortList(head);
        ListNode right = sortList(nextToMid);
        
        // Mezclar las dos mitades ordenadas
        return merge(left, right);
    }
    
    private static ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;
        
        return dummy.next;
    }
    
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        
        System.out.println("Lista original:");
        printList(head);
        
        head = sortList(head);
        
        System.out.println("Lista ordenada:");
        printList(head);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; this.next = null; }
}

