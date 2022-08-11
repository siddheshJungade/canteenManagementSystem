# AWS CodeCommit/Visual Studio (VS) Code
 
 **Setting up AWS CodeCommit**
   * Open: https://991491925675.signin.aws.amazon.com/console in your browser; bookmark this page in your browser
   * Type your IAMS user name as  {hexaware e-mail Id}-Hexaware, e.g, KrishnaKumar-Hexaware  and "hexaware123" as your initial sign-in password
   * After  you sign in for the first time, you must change your password.
    
  **Setting Up the Public and Private Keys for Git and AWS CodeCommit**
    * Step 1: Open the Git Bash emulator; From the emulator, run the ssh-keygen command, and follow the directions to save the file to the .ssh directory for your profile.
    * For example:
      * $ `ssh-keygen`
    * Generating public/private rsa key pair.
    * Enter file in which to save the key (/drive/Users/user-name/.ssh/id_rsa): Pree Enter [to save the key in C:\users\Hvuser\.ssh\id_rsa) 
    * Enter passphrase (empty for no passphrase): Pree Enter [do not use passphrase]
    * Enter same passphrase again: Press Enter 
    * Your identification has been saved in drive/Users/user-name/.ssh/id_rsa.
    * Your public key has been saved in drive/Users/user-name/.ssh/id_rsa.pub.
    * The key fingerprint is:
       * 45:63:d5:99:0e:99:73:50:5e:d4:b3:2d:86:4a:2c:14 user-name@client-name
    * The key's randomart image is:
    ```
+--[ RSA 2048]----+
|        E.+.o*.++|
|        .o .=.=o.|
|       . ..  *. +|
|        ..o . +..|
|        So . . . |
|          .      |
|                 |
|                 |
|                 |
+-----------------+
    ```
    * This generates:
        •	The id_rsa file, which is the private key file.
        •	The id_rsa.pub file, which is the public key file.
   
    * Step 2: Run the following commands to display the value of the public key file (id_rsa.pub)
        `cd .ssh`
        `notepad id_rsa.pub`
    * Copy the contents of the file, and then close Notepad without saving. The contents of the file will look similar to the following:
    ```
    ssh-rsa EXAMPLE-AfICCQD6m7oRw0uXOjANBgkqhkiG9w0BAQUFADCBiDELMAkGA1UEBhMCVVMxCzAJB
    gNVBAgTAldBMRAwDgYDVQQHEwdTZWF0dGxlMQ8wDQYDVQQKEwZBbWF6b24xFDASBgNVBAsTC0lBTSBDb2
    5zb2xlMRIwEAYDVQQDEwlUZXN0Q2lsYWMxHzAdBgkqhkiG9w0BCQEWEG5vb25lQGFtYXpvbi5jb20wHhc
    NMTEwNDI1MjA0NTIxWhcNMTIwNDI0MjA0NTIxWjCBiDELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAldBMRAw
    DgYDVQQHEwdTZWF0dGxlMQ8wDQYDVQQKEwZBbWF6b24xFDAS=EXAMPLE user-name@computer-name
    ```
    * Step 3: Sign in to the AWS Management Console and open the IAM console at https://console.aws.amazon.com/iam/.
    
    * Step 4: In the IAM console, in the navigation pane, choose Users, and from the list of users, choose your IAM user
    
    * Step 5: On the user details page, choose the Security Credentials tab, and then choose Upload SSH public key.
    
    * Step 6: Paste the contents of your SSH public key into the field, and then choose Upload SSH public key
    
    * Step 7: Copy or save the information in SSH Key ID (for example, APKAEIBAERJR2EXAMPLE).
    
    * Step 8: In the Bash emulator, type the following commands to create a config file in the ~/.ssh directory, or edit it if one already exists:
        `cd .. [if you are in .ssh directory already]`
        `vi ~/.ssh/config`
    * Step 9: Add the following lines to the file, where the value for User is the SSH key ID you copied earlier, and the value for 
    [User shift+ p to copy the following lines in vi editor]
    ```
Host git-codecommit.*.amazonaws.com
User APKAEIBAERJR2EXAMPLE   [user i(insertmode)to edit the user id]
IdentityFile ~/.ssh/id_rsa
    ```
      * Use :wq to save the file 
      
    * Step 10: Run the following command to test your SSH configuration:
     * `ssh git-codecommit.us-east-2.amazonaws.com`
     * You will be asked to confirm the connection because git-codecommit.us-east-2.amazonaws.com is not yet included in your known hosts file. 
     * The AWS CodeCommit server fingerprint is displayed as part of the verification (a9:6d:03:ed:08:42:21:be:06:e1:e0:2a:d1:75:31:5e for MD5 or 
     *                                                                                   3lBlW2g5xn/NA2Ck6dyeJIrQOWvn7n8UEs56fG6ZIzQ for SHA256).
  * Command Line/Gitbash --  Open Gitbash
    * `pwd` -- Check and verify that the current working directory is C:\users\Hvuser
    * `cd workspace` -- change current working to workspace
    * `pwd` -- Check and verify that the current working directory is C:\users\Hvuser/workspace
        
  * Git (setup/checkout/clone/pull/push)
    * `git --version` // should be atleast 2.14+
    * `git config --global -l` // should throw an error
    * `git config --global user.name "<your name>"`
    * `git config --global user.email <your email>`
    * `git config --global -l`

  ** Clone repository at Gitbash **
    * Open https://console.aws.amazon.com/codecommit
    * Use the following URL to access your repository
    * https://us-east-2.console.aws.amazon.com/codesuite/codecommit/repositories/CanteenManagementSystem/browse?region=us-east-2
    * Choose Clone URL, and then copy the SSH URL.    
    * Go back to Gitbash and clone the project
    * `pwd` -- Check and verify that the current working directory is C:\users\Hvuser/workspace
    * `git clone ssh://git-codecommit.us-east-2.amazonaws.com/v1/repos/CanteenManagementSystem`
     