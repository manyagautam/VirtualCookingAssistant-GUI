import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class VirtualCookingAssistantGUI extends JFrame {

    private JTextArea recipeTextArea;
    private JButton startButton;
    private JButton nutritionalInfoButton;
    private JTextArea nutritionalInfoTextArea;
    private JTextArea cookingInstructionsTextArea;
    private JButton nextStepButton;
    private JButton suggestSubstitutionButton;
    private JTextArea substitutionSuggestionsTextArea;

    private ArrayList<String> cookingSteps;
    private int currentStepIndex;

    public VirtualCookingAssistantGUI() {
        setTitle("Virtual Cooking Assistant");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); // Set background color

        // Initialize components
        recipeTextArea = new JTextArea();
        startButton = new JButton("Start Cooking");
        nutritionalInfoButton = new JButton("Nutritional Information");
        nutritionalInfoTextArea = new JTextArea();
        cookingInstructionsTextArea = new JTextArea();
        nextStepButton = new JButton("Next Step");
        suggestSubstitutionButton = new JButton("Suggest Substitutions");
        substitutionSuggestionsTextArea = new JTextArea();

        // Set fonts and colors
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = (Color.WHITE); // White

        recipeTextArea.setBackground(Color.DARK_GRAY); // Set text area background color
        recipeTextArea.setForeground(Color.WHITE); // Set text area text color
        recipeTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set text area font

        startButton.setFont(buttonFont);
        startButton.setBackground(buttonColor);
        startButton.setForeground(Color.BLACK);

        nutritionalInfoButton.setFont(buttonFont);
        nutritionalInfoButton.setBackground(buttonColor);
        nutritionalInfoButton.setForeground(Color.BLACK);

        nutritionalInfoTextArea.setBackground(Color.DARK_GRAY); // Set text area background color
        nutritionalInfoTextArea.setForeground(Color.WHITE); // Set text area text color
        nutritionalInfoTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set text area font

        cookingInstructionsTextArea.setBackground(Color.DARK_GRAY); // Set text area background color
        cookingInstructionsTextArea.setForeground(Color.WHITE); // Set text area text color
        cookingInstructionsTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set text area font

        nextStepButton.setFont(buttonFont);
        nextStepButton.setBackground(buttonColor);
        nextStepButton.setForeground(Color.BLACK);

        suggestSubstitutionButton.setFont(buttonFont);
        suggestSubstitutionButton.setBackground(buttonColor);
        suggestSubstitutionButton.setForeground(Color.BLACK);

        substitutionSuggestionsTextArea.setBackground(Color.DARK_GRAY); // Set text area background color
        substitutionSuggestionsTextArea.setForeground(Color.WHITE); // Set text area text color
        substitutionSuggestionsTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set text area font

        // Set borders
        Border textBorder = new LineBorder(Color.WHITE, 2); // Set text area border
        recipeTextArea.setBorder(textBorder);
        nutritionalInfoTextArea.setBorder(textBorder);
        cookingInstructionsTextArea.setBorder(textBorder);
        substitutionSuggestionsTextArea.setBorder(textBorder);

        // Add components to the frame
        JPanel recipePanel = new JPanel(new BorderLayout());
        recipePanel.setBackground(Color.BLACK);
        JLabel recipeLabel = new JLabel("Recipe:");
        recipeLabel.setForeground(Color.WHITE); // Set label text color to white
        recipePanel.add(recipeLabel, BorderLayout.NORTH);
        recipePanel.add(new JScrollPane(recipeTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(startButton);
        buttonPanel.add(nutritionalInfoButton);

        JPanel nutritionalInfoPanel = new JPanel(new BorderLayout());
        nutritionalInfoPanel.setBackground(Color.BLACK);
        JLabel nutritionalInfoLabel = new JLabel("Nutritional Information:");
        nutritionalInfoLabel.setForeground(Color.WHITE); // Set label text color to white
        nutritionalInfoPanel.add(nutritionalInfoLabel, BorderLayout.NORTH);
        nutritionalInfoPanel.add(new JScrollPane(nutritionalInfoTextArea), BorderLayout.CENTER);

        JPanel cookingInstructionsPanel = new JPanel(new BorderLayout());
        cookingInstructionsPanel.setBackground(Color.BLACK);
        JLabel cookingInstructionsLabel = new JLabel("Cooking Instructions:");
        cookingInstructionsLabel.setForeground(Color.WHITE); // Set label text color to white
        cookingInstructionsPanel.add(cookingInstructionsLabel, BorderLayout.NORTH);
        cookingInstructionsPanel.add(new JScrollPane(cookingInstructionsTextArea), BorderLayout.CENTER);
        cookingInstructionsPanel.add(nextStepButton, BorderLayout.SOUTH);

        JPanel substitutionPanel = new JPanel(new BorderLayout());
        substitutionPanel.setBackground(Color.BLACK);
        JLabel substitutionLabel = new JLabel("Ingredient Substitution Suggestions:");
        substitutionLabel.setForeground(Color.WHITE); // Set label text color to white
        substitutionPanel.add(substitutionLabel, BorderLayout.NORTH);
        substitutionPanel.add(new JScrollPane(substitutionSuggestionsTextArea), BorderLayout.CENTER);
        substitutionPanel.add(suggestSubstitutionButton, BorderLayout.SOUTH);

        add(recipePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(nutritionalInfoPanel, BorderLayout.EAST);
        add(cookingInstructionsPanel, BorderLayout.WEST);
        add(substitutionPanel, BorderLayout.NORTH);

        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCooking();
            }
        });

        nutritionalInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNutritionalInfo();
            }
        });

        nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextStep();
            }
        });

        suggestSubstitutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suggestSubstitutions();
            }
        });

        setVisible(true);
    }

    private void startCooking() {
        cookingSteps = new ArrayList<>(Arrays.asList(cookingInstructionsTextArea.getText().split("\n"))); // Split text by newline and convert to ArrayList
        System.out.println(cookingSteps);
        // Reset the cooking instructions
        cookingInstructionsTextArea.setText("");
        currentStepIndex = 0;

        // Display the first cooking step
        displayNextStep();

        JOptionPane.showMessageDialog(null, "Cooking started!");
    }

    private void displayNextStep() {
        if (currentStepIndex < cookingSteps.size()) {
            String currentStep = cookingSteps.get(currentStepIndex);
            cookingInstructionsTextArea.append((currentStepIndex + 1) + ". " + currentStep + "\n");
            currentStepIndex++;
        } else {
            JOptionPane.showMessageDialog(null, "No more steps.");
        }
    }

    private void displayNutritionalInfo() {
        // Hardcoded nutritional information for demonstration purposes
        String nutritionalInfo = "Calories: 300\nProtein: 15g\nCarbohydrates: 45g\nFat: 8g\nAllergens: None";
        nutritionalInfoTextArea.setText(nutritionalInfo);
    }

    private void suggestSubstitutions() {
        // Hardcoded substitution suggestions for demonstration purposes
        String substitutionSuggestions = "Flour -> Whole wheat flour\nSugar -> Honey\nButter -> Coconut oil";
        substitutionSuggestionsTextArea.setText(substitutionSuggestions);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VirtualCookingAssistantGUI();
            }
        });
    }
}
