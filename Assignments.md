# Assignments

All the files which you will need to edit in these assignments are located in **src/test/java/com/project**.
> It might be a good idea to open the local html file that is used in the first assignments in a chrome browser (**project/resources/website/index.html**).

## Exercise 1

 1. In **loan.feature**, Right-click on the scenario name 'request a car loan' and choose 'Run Scenario...'
 2. As you can see the webpage gives an error message because the desired amount to borrow is lower than the minimum. Update the step "**And** the amount I want to borrow is '1000'" so that this error message is no longer given.
     > The steps are located in **steps/loan_steps**
 3. Create the step "**And** I continue to explanation" (just remove the #). The background color of the step will turn into brown box, this means that there is no java connected to this step. Use Alt + Enter, choose 'Create step definition', choose loan_steps (com.project.steps).
 4. Check the generated java code, remove the auto generated `throw new PendingException();`. This exception is generated to make sure that you understand java code a bit before you can run a successful execution.
 5. Use Chrome to open **index.html** and right-click the "Continue to explanation" button and select "Inspect" to identify the selector for this button.
 6. In IntelliJ use copy paste from the `iSelectLoanTypeCarLoan()` method, change the CSS selector to selector you found in Chrome.
 7. Is `.click()` the right method to use here?
 8. Run the scenario

## Exercise 2

1.  Create steps that allows you to continue to the personal data page
2.  Create new steps for every field on the page. For every step, copy paste the `findElement`, change the selector and use the correct interaction.
    > The CheatSheet.docx has tips for finding unique selectors.
3. Can you upload a file?

## Exercise 3

1. Create a step after "**Given** I have opened the loan request page" to click the different options under "Why do you need a loan?".
2. Create a step to close the pop-up. What issues do you face?
    > Use smart waits if elements are not immediately available.

## Exercise 4

1. Parameterize the input data in the step "**And** the amount I want to borrow is '1000'". (See Exercise 1.2)
2. Can you parameterize other data as well?

## Exercise 5

1. Create a second scenario in the same feature file.
2. Reuse the steps from the first scenario.
3. Execute the second scenario.

## Exercise 6

1. Use faker to generate random data?
2. Reduce your gherkin to Given When Then (so 3 lines, max 4/5 lines)?
3. Add pages layer?