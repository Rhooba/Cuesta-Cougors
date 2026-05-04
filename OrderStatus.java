/**
 * Represents the lifecycle states of an Order in the system.
 *
 * Typical flow:
 * PLACED → ASSIGNED → PICKED_UP → DELIVERED
 *
 * Additional state:
 * - CANCELLED: order was terminated before completion
 *
 * Notes:
 * - State transitions are not enforced here; handled externally (Order / Driver logic)
 * - ASSIGNED occurs when a Driver is set on the Order
 *
 * @author
 * @version 1.1
 */
public enum OrderStatus {
    PLACED,
    ASSIGNED,
    PICKED_UP,
    DELIVERED,
    CANCELLED,
    ACCEPTED
}
