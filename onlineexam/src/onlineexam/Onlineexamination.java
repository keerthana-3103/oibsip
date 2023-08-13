package onlineexam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
	private String username;
	private String password;
	private String name;
	private String email;
    private Map<String, String> selectedAnswers;

    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.selectedAnswers=new HashMap<>();
    }
        
        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
        
        public String getEmail() {
            return email;
        }
        

        public void setPassword(String newPassword) {
            password = newPassword;
        }

        public void setProfile(String newName,String newEmail) {
            name=newName;
            email=newEmail;
        }
        
        public void selectAnswer(String question,String answer) {
        	selectedAnswers.put(question, answer);
        }
        
        public String getSelectedAnswers(String question) {
        	return selectedAnswers.get(question);
        }
}

class OnlineExamSystem {
	private Map<String, User> users;
    public User loggedInUser;

    public OnlineExamSystem() {
        users = new HashMap<>();
}
    public void register(String username, String password, String name,String email) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, name, email));
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists.");
        }
    }
    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                String loggedInUserName=username;
                System.out.println("                 Welcome, "+loggedInUserName+" !");
                return true;
            }
        }
        System.out.println("Login failed. Invalid credentials.");
        return false;
    }

    public void updatePassword(String newPassword) {
        if (loggedInUser != null) {
            loggedInUser.setPassword(newPassword);
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void updateProfile(String newName, String newEmail) {
        if (loggedInUser != null) {
            loggedInUser.setProfile(newName,newEmail);
            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("You are not logged in.");
        }
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }
}


public class Onlineexamination {
    public static void main(String[] args) {
        OnlineExamSystem examSystem = new OnlineExamSystem();
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun=true;
        boolean wasLoggedIn=false;
        System.out.println("                 Welcome to the Online Examination System!");

        while (shouldRun) {
        	System.out.println("");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	 System.out.print("Enter username: ");
                     String username = scanner.nextLine();
                     System.out.print("Enter password: ");
                     String password = scanner.nextLine();
                     System.out.print("Enter name: ");
                     String name = scanner.nextLine();
                     System.out.print("Enter email: ");
                     String email=scanner.nextLine();		
                     examSystem.register(username, password, name, email);
                     break;

                case 2:
                	System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    examSystem.login(loginUsername, loginPassword);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
            
            if (examSystem.loggedInUser != null) {
            	wasLoggedIn=true;
                while (true) {
                    System.out.println(" ");
                    System.out.println("1. Update Password");
                    System.out.println("2. Update Profile");
                    System.out.println("3. Start Exam");
                    System.out.println("4. Logout");
                    System.out.print("Choose an option: ");
                    int choice2 = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    int score=0;

                    switch (choice2) {
                        case 1:
                        	 if (examSystem.loggedInUser != null) {
                                 System.out.print("Enter new password: ");
                                 String newPassword = scanner.nextLine();
                                 examSystem.updatePassword(newPassword);
                             } else {
                                 System.out.println("You are not logged in.");
                             }
                            break;

                        case 2:
                            if (examSystem.loggedInUser != null) {
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();
                                examSystem.updateProfile(newName,newEmail);
                            } else {
                                System.out.println("You are not logged in.");
                            }

                            break;

                        case 3:
                        	System.out.println("subject: Java Programming");
                        	System.out.println("");
                        	System.out.println("Question 1: Which package contains the Random class?");
                            System.out.println("A. java.util package");
                            System.out.println("B. java.lang package");
                            System.out.println("C. java.awt package");
                            System.out.println("D. java.io package");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer1 = scanner.nextLine();
                            if (userAnswer1.equalsIgnoreCase("A")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }

                            System.out.println("\nQuestion 2: Which of the following is not a Java features?");
                            System.out.println("A. Dynamic");
                            System.out.println("B. Architectural Neutral");
                            System.out.println("C. Use of pointers");
                            System.out.println("D. Object-oriented");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer2 = scanner.nextLine();
                            if (userAnswer2.equalsIgnoreCase("C")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 3: _____ is used to find and fix bugs in the java programs.");
                            System.out.println("A. JVM");
                            System.out.println("B. JRK");
                            System.out.println("C. JDK");
                            System.out.println("D. JDB");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer3 = scanner.nextLine();
                            if (userAnswer3.equalsIgnoreCase("D")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 4: What is the return type of the hashCode() method in the Object class?");
                            System.out.println("A. Object");
                            System.out.println("B. int");
                            System.out.println("C. long");
                            System.out.println("D. void");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer4 = scanner.nextLine();
                            if (userAnswer4.equalsIgnoreCase("B")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 5: Which of the following is a marker interface?");
                            System.out.println("A. Runnable interface");
                            System.out.println("B. Remote interface");
                            System.out.println("C. Readable interface");
                            System.out.println("D. Result interface");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer5 = scanner.nextLine();
                            if (userAnswer5.equalsIgnoreCase("B")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 6: In java,jar stands for_____");
                            System.out.println("A. Java Archive Runner");
                            System.out.println("B. Java Application Resource");
                            System.out.println("C. Java Application Runner");
                            System.out.println("D. None of the above");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer6 = scanner.nextLine();
                            if (userAnswer6.equalsIgnoreCase("D")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 7: How many threads can be executed at a time?");
                            System.out.println("A. Only one thread");
                            System.out.println("B. Multiple threads");
                            System.out.println("C. Only one (main() method) thread");
                            System.out.println("D. Two threads");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer7 = scanner.nextLine();
                            if (userAnswer7.equalsIgnoreCase("B")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 8: What is the default encoding for an OutputStreamWriter?");
                            System.out.println("A. UTF-8");
                            System.out.println("B. Default encoding of the host platform");
                            System.out.println("C. UTF-12");
                            System.out.println("D. None of the above");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer8 = scanner.nextLine();
                            if (userAnswer8.equalsIgnoreCase("B")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 9: If a thread goes to sleep");
                            System.out.println("A. It releases all the lock it has.");
                            System.out.println("B. It does not release any locks.");
                            System.out.println("C. It releases half of its locks.");
                            System.out.println("D. It releases all of its lock except one.");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer9 = scanner.nextLine();
                            if (userAnswer9.equalsIgnoreCase("B")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }
                            
                            System.out.println("\nQuestion 10: Which of the following modifiers can be used for a variable so that it can be accessed by any thread or a part of a program?");
                            System.out.println("A. global");
                            System.out.println("B. transient");
                            System.out.println("C. volatile");
                            System.out.println("D. default");
                            System.out.print("Select your answer (A/B/C/D): ");
                            String userAnswer10 = scanner.nextLine();
                            if (userAnswer10.equalsIgnoreCase("C")) {
                                System.out.println("Correct!");
                                score++;
                            } else {
                                System.out.println("Incorrect.");
                            }

                            System.out.println("\n                          .......................Thank you for completing the exam!......................");
                            System.out.println("");
                            System.out.println("Your final score: " + score + " out of 10");

                            break;

                        case 4:
                            examSystem.logout();
                            wasLoggedIn=false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }
                    if (!wasLoggedIn) {
                        break;
                    }
                }

            } else if (wasLoggedIn) {
                System.out.println("Logged out successfully.");
                wasLoggedIn = false;  
            }
        }
    }
}
                
        
        
 





                
            	