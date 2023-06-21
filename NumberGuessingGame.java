import javax.swing.JOptionPane;

public class NumberGuessingGame 
{
    public static void main(String[] args) 
    {
        int totalRound = 5; // Change this value to set the number of rounds
        int randomNo;
        
        for (int round = 1; round <= totalRound; round++) 
        {
            randomNo = generateRandomNo(1, 50);
            playGame(randomNo, round, totalRound);
        }
        
        JOptionPane.showMessageDialog(null, "Game Over!");
    }

    private static int generateRandomNo(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

    private static void playGame(int randomNo, int round, int totalRound) 
    {
        boolean guessed = false;
        int difference = 10;
        int attempt = 0;
        int score = 100;
        int maxAttempts = 7; // Change this value to set the maximum number of attempts per round
        
        JOptionPane.showMessageDialog(null, "Round " + round + " of " + totalRound + "\nGuess a number between 1 and 50.");

        while (!guessed && attempt < maxAttempts) 
        {
            String guessStr = JOptionPane.showInputDialog(null, "Attempt: " + (attempt + 1) + "\nEnter your guess:");
            int guess = Integer.parseInt(guessStr);
            attempt++;

            if (guess < randomNo)
            {
                if ((randomNo-guess) <= difference) 
                {
                    JOptionPane.showMessageDialog(null, "Low, but close enough! Try again.");
                    score -= 5;
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                    score -= 10;
                }
            }  
            
            else if (guess > randomNo)  
            {
                if ((guess-randomNo) <= difference)
                {
                    JOptionPane.showMessageDialog(null, "High, but close enough! Try again.");
                    score -= 5;
                }
                
                else 
                {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                    score -= 10;
                }
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number " + randomNo + " correctly in " + attempt + " attempts! Your score is: " + score);
                guessed = true;
            }  
        }
        
        if (!guessed) 
        {
            JOptionPane.showMessageDialog(null, "You failed to guess the number. The correct number was: " + randomNo);
        }
    }
}