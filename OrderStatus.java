/**
 * Represents the possible statuses of an Order in the system.
 *
 * Status flow: PLACED --> ACCEPTED --> IN_PROGRESS --> DELIVERED
 *
 * @author
 * @version 1.0
 */
public enum OrderStatus {
    PLACED,
    ACCEPTED,
    IN_PROGRESS,
    DELIVERED
}
