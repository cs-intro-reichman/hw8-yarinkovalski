/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
        
    }

    public int getUserCount(){
        return this.userCount;
    }


    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        //// Replace the following statement with your code
        String newName = "" + name.charAt(0);
        newName = newName.toUpperCase();
        newName += name.substring(1,name.length());
        for (int i=0; i<this.users.length; i++)
        {
            if (this.users[i] != null && this.users[i].getName().equals(newName))
                return this.users[i];
        }

        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        //// Replace the following statement with your code
        if (name==null){
            return false;
        }
        String newName = "" + name.charAt(0);
        newName = newName.toUpperCase();
        newName += name.substring(1,name.length());
        if (this.userCount==this.users.length)
            return false;
        
        for (int i=0; i<this.userCount; i++)
        {
            if (this.users[i].getName().equals(newName))
                return false;
        }

        User addedUser = new User(newName);
        this.users[userCount]=addedUser;
        this.userCount++;
        
        return true;   
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        //// Replace the following statement with your code
        if (name1==null){
            return false;
        }
        if (name2==null){
            return false;
        }
        
        String newName1 = "" + name1.charAt(0);
        newName1 = newName1.toUpperCase();
        newName1 += name1.substring(1,name1.length());
        String newName2 = "" + name2.charAt(0);
        newName2 = newName2.toUpperCase();
        newName2 += name2.substring(1,name2.length());
        int i=0;
        while (i<this.userCount && !this.users[i].getName().equals(newName1))
            i++;
        
        if (i==this.userCount) //if network is full return false
            return false;

        return this.users[i].addFollowee(newName2);
        
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        //// Replace the following statement with your code
        String newName = "" + name.charAt(0);
        newName = newName.toUpperCase();
        newName += name.substring(1,name.length());
        int i_user=0;
        while (i_user<this.userCount && !this.users[i_user].getName().equals(newName))
            i_user++;

        if (i_user==this.userCount) //if network is full return false
            return null;

        int max =-1; //the maximum of mutual friends
        int index=-1; //the index of the recommend user
        for (int j=0; j<this.userCount; j++)
        {
            if (j==i_user)
                continue;

            int mutual = users[i_user].countMutual(this.users[j]);
            if (mutual>max)
            {
                max = mutual;
                index = j;
            }
        }
        if (index==-1)
            return null;

        return this.users[index].getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        //// Replace the following statement with your code
        
        int popular=0;
        int index_popular=-1;
        for (int i=0; i<this.userCount; i++)
        {
            int count_folowee = followeeCount(this.users[i].getName());
            if (count_folowee>popular)
            {
                popular = count_folowee;
                index_popular = i;
            }
        }
        if (index_popular==-1)
            return null;
            
        return users[index_popular].getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        //// Replace the following statement with your code
        String newName = "" + name.charAt(0);
        newName = newName.toUpperCase();
        newName += name.substring(1,name.length());
        
        
        int i=0;
        while (i<this.userCount && !this.users[i].getName().equals(newName))
            i++;
        
        if (i==this.userCount) //if network is full return false
            return 0;

        int sum=0;

        for (int j=0; j<this.userCount; j++)
        {
            if (i!=j && users[j].follows(this.users[i].getName()))
                sum++;
            
        }

        return sum;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       //// Replace the following statement with your code
       String ans = "";
       for (int j=0; j<this.userCount; j++)
       {
        ans = ans + this.users[j].toString()+ "\n";  
       }

       return ans;
    }
}
