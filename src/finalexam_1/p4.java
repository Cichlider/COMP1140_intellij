package finalexam_1;

/**
 * Final Exam - Question 4: Train Platform Simulation
 *
 * Description:
 * A train station has:
 * - One inbound track (length: 8)
 * - One platform X (length: 9, calculated from 25 - 8 - 8)
 * - One outbound track (length: 8)
 * - Total length: 25
 *
 * Rules:
 * - Trains have length between 1-8 units
 * - Trains must stop at the platform for at least 3 seconds
 * - Trains move: Inbound → Platform → Outbound
 */

// ════════════════════════════════════════════
// 1. ENUM: Train Status
// ════════════════════════════════════════════

/**
 * Represents the current status of a train in the station system
 */
enum TrainStatus {
    WAITING,        // Train is waiting to enter
    INBOUND,        // Train is on the inbound track
    AT_PLATFORM,    // Train is stopped at the platform
    OUTBOUND,       // Train is on the outbound track
    DEPARTED        // Train has left the station
}


// ════════════════════════════════════════════
// 2. RECORD: Train Information
// ════════════════════════════════════════════

/**
 * Record class representing a train
 * @param id - unique identifier for the train
 * @param length - length of the train (1-8 units)
 */
record Train(int id, int length) {
    // Constructor validation
    public Train {
        if (length < 1 || length > 8) {
            throw new IllegalArgumentException(
                    "Train length must be between 1 and 8, got: " + length
            );
        }
    }

    @Override
    public String toString() {
        return "Train[id=" + id + ", length=" + length + "]";
    }
}


// ════════════════════════════════════════════
// 3. INTERFACE: Train Provider
// ════════════════════════════════════════════

/**
 * Interface for objects that can provide train information
 */
interface TrainProvider {
    /**
     * Gets the train object
     * @return the Train record
     */
    Train getTrain();
}


// ════════════════════════════════════════════
// 4. CLASS: Train Instance (with status)
// ════════════════════════════════════════════

/**
 * Represents a train instance with its current status and timing
 */
class TrainInstance implements TrainProvider {
    private final Train train;
    private TrainStatus status;
    private long platformArrivalTime;  // When train arrived at platform (milliseconds)
    private static final long MIN_PLATFORM_TIME = 3000;  // 3 seconds in milliseconds

    public TrainInstance(Train train) {
        this.train = train;
        this.status = TrainStatus.WAITING;
        this.platformArrivalTime = 0;
    }

    @Override
    public Train getTrain() {
        return train;
    }

    public TrainStatus getStatus() {
        return status;
    }

    public void setStatus(TrainStatus status) {
        this.status = status;
        if (status == TrainStatus.AT_PLATFORM) {
            platformArrivalTime = System.currentTimeMillis();
        }
    }

    /**
     * Check if train has been at platform for minimum required time
     */
    public boolean canLeavePlatform() {
        if (status != TrainStatus.AT_PLATFORM) {
            return false;
        }
        long elapsedTime = System.currentTimeMillis() - platformArrivalTime;
        return elapsedTime >= MIN_PLATFORM_TIME;
    }

    @Override
    public String toString() {
        return train + " [Status: " + status + "]";
    }
}


// ════════════════════════════════════════════
// 5. CLASS: Platform System
// ════════════════════════════════════════════

/**
 * Manages the train platform system
 */
class PlatformSystem {
    private static final int INBOUND_LENGTH = 8;
    private static final int PLATFORM_LENGTH = 9;
    private static final int OUTBOUND_LENGTH = 8;
    private static final int TOTAL_LENGTH = 25;

    private TrainInstance inboundTrain = null;
    private TrainInstance platformTrain = null;
    private TrainInstance outboundTrain = null;

    /**
     * Try to add a train to the inbound track
     */
    public boolean addTrainToInbound(Train train) {
        if (inboundTrain != null) {
            System.out.println("❌ Inbound track is occupied!");
            return false;
        }

        if (train.length() > INBOUND_LENGTH) {
            System.out.println("❌ Train too long for inbound track!");
            return false;
        }

        inboundTrain = new TrainInstance(train);
        inboundTrain.setStatus(TrainStatus.INBOUND);
        System.out.println("✅ " + train + " entered inbound track");
        return true;
    }

    /**
     * Move train from inbound to platform
     */
    public boolean moveInboundToPlatform() {
        if (inboundTrain == null) {
            System.out.println("❌ No train on inbound track!");
            return false;
        }

        if (platformTrain != null) {
            System.out.println("❌ Platform is occupied!");
            return false;
        }

        if (inboundTrain.getTrain().length() > PLATFORM_LENGTH) {
            System.out.println("❌ Train too long for platform!");
            return false;
        }

        platformTrain = inboundTrain;
        platformTrain.setStatus(TrainStatus.AT_PLATFORM);
        inboundTrain = null;
        System.out.println("✅ " + platformTrain.getTrain() + " arrived at platform");
        return true;
    }

    /**
     * Move train from platform to outbound (must wait 3 seconds)
     */
    public boolean movePlatformToOutbound() {
        if (platformTrain == null) {
            System.out.println("❌ No train at platform!");
            return false;
        }

        if (!platformTrain.canLeavePlatform()) {
            System.out.println("❌ Train must stay at platform for at least 3 seconds!");
            return false;
        }

        if (outboundTrain != null) {
            System.out.println("❌ Outbound track is occupied!");
            return false;
        }

        if (platformTrain.getTrain().length() > OUTBOUND_LENGTH) {
            System.out.println("❌ Train too long for outbound track!");
            return false;
        }

        outboundTrain = platformTrain;
        outboundTrain.setStatus(TrainStatus.OUTBOUND);
        platformTrain = null;
        System.out.println("✅ " + outboundTrain.getTrain() + " moved to outbound track");
        return true;
    }

    /**
     * Remove train from outbound track (departs station)
     */
    public boolean removeFromOutbound() {
        if (outboundTrain == null) {
            System.out.println("❌ No train on outbound track!");
            return false;
        }

        Train departedTrain = outboundTrain.getTrain();
        outboundTrain.setStatus(TrainStatus.DEPARTED);
        outboundTrain = null;
        System.out.println("✅ " + departedTrain + " departed from station");
        return true;
    }

    /**
     * Display current state of the platform system
     */
    public void displayStatus() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         PLATFORM SYSTEM STATUS                 ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        System.out.println("Inbound Track (8):  " +
                (inboundTrain != null ? inboundTrain.getTrain() : "Empty"));

        System.out.println("Platform (9):       " +
                (platformTrain != null ? platformTrain.getTrain() : "Empty"));

        System.out.println("Outbound Track (8): " +
                (outboundTrain != null ? outboundTrain.getTrain() : "Empty"));

        System.out.println("─────────────────────────────────────────────────\n");
    }
}


// ════════════════════════════════════════════
// 6. MAIN: Test Program
// ════════════════════════════════════════════

public class p4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║   Train Platform Simulation - Question 4      ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();

        // Run basic test
        System.out.println("═══ Test 1: Basic Flow ═══");
        testBasicFlow();

        System.out.println("\n═══ Test 2: Platform Timing ═══");
        testPlatformTiming();

        System.out.println("\n═══ Test 3: Multiple Trains ═══");
        testMultipleTrains();

        System.out.println("\n═══ Test 4: Invalid Operations ═══");
        testInvalidOperations();
    }


    /**
     * Test 1: Basic train flow through system
     */
    private static void testBasicFlow() throws InterruptedException {
        PlatformSystem platform = new PlatformSystem();
        Train train1 = new Train(101, 5);

        platform.displayStatus();

        // Add train to inbound
        platform.addTrainToInbound(train1);
        platform.displayStatus();

        // Move to platform
        Thread.sleep(1000);
        platform.moveInboundToPlatform();
        platform.displayStatus();

        // Wait 3 seconds then move to outbound
        System.out.println("⏱️  Waiting 3 seconds at platform...");
        Thread.sleep(3000);
        platform.movePlatformToOutbound();
        platform.displayStatus();

        // Depart
        platform.removeFromOutbound();
        platform.displayStatus();
    }


    /**
     * Test 2: Platform minimum time requirement
     */
    private static void testPlatformTiming() throws InterruptedException {
        PlatformSystem platform = new PlatformSystem();
        Train train = new Train(102, 3);

        platform.addTrainToInbound(train);
        platform.moveInboundToPlatform();

        System.out.println("⏱️  Trying to leave immediately (should fail)...");
        Thread.sleep(1000);  // Only wait 1 second
        platform.movePlatformToOutbound();  // Should fail

        System.out.println("⏱️  Waiting additional 2+ seconds...");
        Thread.sleep(2500);  // Wait total 3.5 seconds
        platform.movePlatformToOutbound();  // Should succeed
        platform.displayStatus();
    }


    /**
     * Test 3: Multiple trains in sequence
     */
    private static void testMultipleTrains() throws InterruptedException {
        PlatformSystem platform = new PlatformSystem();

        Train train1 = new Train(201, 4);
        Train train2 = new Train(202, 6);
        Train train3 = new Train(203, 2);

        // Train 1
        platform.addTrainToInbound(train1);
        platform.moveInboundToPlatform();

        // Train 2 enters while Train 1 at platform
        Thread.sleep(1000);
        platform.addTrainToInbound(train2);
        platform.displayStatus();

        // Move Train 1 out
        Thread.sleep(2500);
        platform.movePlatformToOutbound();
        platform.removeFromOutbound();

        // Move Train 2 to platform
        platform.moveInboundToPlatform();

        // Train 3 enters
        platform.addTrainToInbound(train3);
        platform.displayStatus();

        System.out.println("✅ Successfully handling multiple trains!");
    }


    /**
     * Test 4: Invalid operations
     */
    private static void testInvalidOperations() {
        PlatformSystem platform = new PlatformSystem();

        System.out.println("Test 4.1: Train too long");
        Train longTrain = new Train(301, 10);  // Should throw exception
    }
}
