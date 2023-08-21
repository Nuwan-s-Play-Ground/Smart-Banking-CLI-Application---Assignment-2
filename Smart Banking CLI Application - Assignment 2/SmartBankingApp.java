import java.util.Scanner;

public class SmartBankingApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[32;1m";
        final String COLOR_YELLO_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String CREATE_ACCOUNT = "Create New Acount";
        final String DEPOSIT = "Deposit";
        final String WITHDRAWAL = "Withdrawal";
        final String TRANSFER = "Transfer";
        final String PRINT_STATEMENT = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[][] bankClients=new String[0][];

        // String[] accountNumbers = new String[0];
        // String[] customerNames = new String[0];
        // Double[] accountBalance = new Double[0];

        String screen = DASHBOARD;
        int i = 1;

        loopOne: 
        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.printf("\t%s+%s+%s\n", COLOR_YELLO_BOLD, "-".repeat(APP_TITLE.length() - 11), RESET);
            System.out.print("\t|" + APP_TITLE + "|\n");
            System.out.printf("\t%s+%s+%s\n", COLOR_YELLO_BOLD, "-".repeat(APP_TITLE.length() - 11), RESET);
            switch (screen) {
                case DASHBOARD:
                    System.out.printf("\t[1]. %s\n", CREATE_ACCOUNT);
                    System.out.printf("\t[2]. %s\n", DEPOSIT);
                    System.out.printf("\t[3]. %s\n", WITHDRAWAL);
                    System.out.printf("\t[4]. %s\n", TRANSFER);
                    System.out.printf("\t[5]. %s\n", PRINT_STATEMENT);
                    System.out.printf("\t[6]. %s\n", DELETE_ACCOUNT);
                    System.out.printf("\t[7]. %s\n", "Exsit");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = CREATE_ACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAWAL;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = PRINT_STATEMENT;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;

                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:
                            continue;
                    }
                    break;

                case CREATE_ACCOUNT:

                    String id = String.format("SDB-" + "%05d", i);
                    System.out.printf("\tID: %s\n", id);
                    i++;
                    String name;
                    boolean valid;
                    double initialAccountBalanceDouble;

                    do {
                        valid = true;
                        System.out.print("\tName: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf(ERROR_MSG, "Name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int j = 0; j < name.length(); j++) {
                            if (!(Character.isLetter(name.charAt(j)) ||
                                    Character.isSpaceChar(name.charAt(j)))) {
                                System.out.printf(ERROR_MSG, "Invalid name");
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);

                    String initialAccountBalance;

                    loopTwo:
                     do {
                        valid = true;
                        System.out.printf("\tInitial Deposit: ");
                        initialAccountBalance = SCANNER.nextLine();
                        if (initialAccountBalance.length() > 4) {
                            for (int j = 0; j < initialAccountBalance.length() - 3; j++) {
                                if (!Character.isDigit(initialAccountBalance.charAt(j))) {
                                    valid = false;
                                    System.out.printf(ERROR_MSG, "Enter a Valid Amount");
                                    continue loopTwo;
                                }
                            }
                            if (!(initialAccountBalance.charAt((initialAccountBalance.length() - 3)) == '.' || Character
                                    .isDigit(initialAccountBalance.charAt(initialAccountBalance.length() - 3)))) {
                                valid = false;
                                System.out.printf(ERROR_MSG, "Enter a Valid Amount ");
                                continue loopTwo;

                            } else {
                                for (int j = initialAccountBalance.length() - 2; j < initialAccountBalance
                                        .length(); j++) {
                                    if (!Character.isDigit(initialAccountBalance.charAt(j))) {
                                        valid = false;
                                        System.out.printf(ERROR_MSG, "Enter a Valid Amount");
                                        continue loopTwo;
                                    }
                                }

                            }
                        }else{
                             for (int j = 0; j < initialAccountBalance.length(); j++) {
                                if (!Character.isDigit(initialAccountBalance.charAt(j))) {
                                    valid = false;
                                    System.out.printf(ERROR_MSG, "Enter a Valid Amount");
                                    continue loopTwo;
                                }
                            }
                        }

                        if (valid) {
                            initialAccountBalanceDouble = Double.parseDouble(initialAccountBalance);
                            if (initialAccountBalanceDouble < 5000) {
                                System.out.printf(ERROR_MSG,
                                        "Insufficient Initial Deposit Minimum Rs.5000.00 Should be deposited");
                                System.out.print("\tDo you Want to Deposit more?(y/n)");
                                if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) {
                                    valid = false;
                                    continue;
                                } else {
                                    valid=false;
                                    i--;
                                    continue loopOne;
                                }

                            }

                        }

                    } while (!valid);

                    initialAccountBalanceDouble = Double.parseDouble(initialAccountBalance);
                    System.out.printf("\tDeposited: " + "%s%,.2f", "Rs.", initialAccountBalanceDouble);

                    // String[] newAccountNumbers = new String[accountNumbers.length + 1];
                    // String[] newCustomerNames = new String[customerNames.length + 1];
                    // Double[] newAccountBalances = new Double[accountBalance.length + 1];

                    /*Let's scale the `tempBankClients` array by one */

                    String[][] tempBankClients = new String[bankClients.length+1][3];
                    for (int j = 0; j < bankClients.length; j++) {

                        tempBankClients[j]=bankClients[j];

                        // newAccountNumbers[j] = accountNumbers[j];
                        // newCustomerNames[j] = customerNames[j];
                        // newAccountBalances[j] = accountBalance[j];
                    }

                    // newAccountNumbers[newAccountNumbers.length - 1] = id;
                    // newCustomerNames[newAccountNumbers.length - 1] = name;
                    // newAccountBalances[newAccountNumbers.length - 1] = initialAccountBalanceDouble;
                    // accountNumbers = newAccountNumbers;
                    // customerNames = newCustomerNames;
                    // accountBalance = newAccountBalances;

                    /*Lets add new clients */
                    tempBankClients[tempBankClients.length-1][0]=id;
                    tempBankClients[tempBankClients.length-1][1]=name;
                    tempBankClients[tempBankClients.length-1][2]=initialAccountBalanceDouble+"";

                    /* Let's swap arrays' memory locations*/
                    bankClients=tempBankClients;
                

                    System.out.println();
                    System.out.printf(SUCCESS_MSG,
                            String.format("%s:%s has been Created successfully", id, name));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    screen = DASHBOARD;
                    break;
                    
                case DEPOSIT:
                
                System.out.print("\nEnter Account ID: ");
                String depositAccountId = SCANNER.nextLine().strip();

                boolean accountFound = false;
                for (String[] client : bankClients) {
                    if (client[0].equalsIgnoreCase(depositAccountId)) {
                        accountFound = true;

                        System.out.printf("\nAccount Holder: %s\n", client[1]);
                        System.out.printf("Current Balance: Rs.%,.2f\n", Double.parseDouble(client[2]));

                        double depositAmount=0;
                        boolean validDeposit = false;

                        do {
                            System.out.print("Enter Deposit Amount: Rs.");
                            try {
                                depositAmount = Double.parseDouble(SCANNER.nextLine().strip());
                                if (depositAmount <= 0) {
                                    System.out.printf(ERROR_MSG, "Amount must be greater than 0");
                                } else {
                                    validDeposit = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.printf(ERROR_MSG, "Invalid input. Please enter a valid number");
                            }
                        } while (!validDeposit);

                        double updatedBalance = Double.parseDouble(client[2]) + depositAmount;
                        client[2] = String.valueOf(updatedBalance);

                        System.out.printf(SUCCESS_MSG, "Deposit successful");
                        System.out.printf("Updated Balance: Rs.%,.2f\n", updatedBalance);
                        break;
                    }
                }

                if (!accountFound) {
                    System.out.printf(ERROR_MSG, "Account not found");
                }

                System.out.print("\nPress Enter to continue...");
                SCANNER.nextLine(); // Wait for user to press Enter
                screen = DASHBOARD;
                break;

                case WITHDRAWAL:
                System.out.print("\nEnter Account ID: ");
                String withdrawalAccountId = SCANNER.nextLine().strip();

                boolean accountFoundWithdrawal = false;
                for (String[] client : bankClients) {
                    if (client[0].equalsIgnoreCase(withdrawalAccountId)) {
                        accountFoundWithdrawal = true;

                        System.out.printf("\nAccount Holder: %s\n", client[1]);
                        System.out.printf("Current Balance: Rs.%,.2f\n", Double.parseDouble(client[2]));

                        double withdrawalAmount = 0;
                        boolean validWithdrawal = false;

                        do {
                            System.out.print("Enter Withdrawal Amount: Rs.");
                            try {
                                withdrawalAmount = Double.parseDouble(SCANNER.nextLine().strip());
                                double currentBalance = Double.parseDouble(client[2]);
                                if (withdrawalAmount <= 0) {
                                    System.out.printf(ERROR_MSG, "Amount must be greater than 0");
                                } else if (withdrawalAmount > currentBalance) {
                                    System.out.printf(ERROR_MSG, "Insufficient balance");
                                } else {
                                    validWithdrawal = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.printf(ERROR_MSG, "Invalid input. Please enter a valid number");
                            }
                        } while (!validWithdrawal);

                        double updatedBalanceWithdrawal = Double.parseDouble(client[2]) - withdrawalAmount;
                        client[2] = String.valueOf(updatedBalanceWithdrawal);

                        System.out.printf(SUCCESS_MSG, "Withdrawal successful");
                        System.out.printf("Updated Balance: Rs.%,.2f\n", updatedBalanceWithdrawal);
                        break;
                    }
                }

                if (!accountFoundWithdrawal) {
                    System.out.printf(ERROR_MSG, "Account not found");
                }

                System.out.print("\nPress Enter to continue...");
                SCANNER.nextLine(); // Wait for user to press Enter
                screen = DASHBOARD;
                break;


            }
            


        } while (true);

    }

}