public class FirstFailingVersion {
    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return fbvHelper(1, n, isBadVersion); }
    public static long fbvHelper(long low, long high, IsFailingVersion isBad) {
        long guess = (high - low) / 2 + low;
        if (isBad.isFailingVersion(guess) && !isBad.isFailingVersion(guess-1)) return guess;
        else if (isBad.isFailingVersion(guess)) return fbvHelper(low, guess, isBad);
        return fbvHelper(guess, high, isBad); } }