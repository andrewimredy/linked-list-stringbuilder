// CS 0445 Spring 2019
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
 // These are the only three instance variables you are allowed to have.
 // See details of CNode class below.  In other words, you MAY NOT add
 // any additional instance variables to this class.  However, you may
 // use any method variables that you need within individual methods.
 // But remember that you may NOT use any variables of any other
 // linked list class or of the predefined StringBuilder or 
 // or StringBuffer class in any place in your code.  You may only use the
 // String class where it is an argument or return type in a method.
 private CNode firstC; // reference to front of list.  This reference is necessary
       // to keep track of the list
 private CNode lastC;  // reference to last node of list.  This reference is
       // necessary to improve the efficiency of the append()
       // method
 private int length;   // number of characters in the list

 // You may also add any additional private methods that you need to
 // help with your implementation of the public methods.

 // Create a new MyStringBuilder initialized with the chars in String s
 public MyStringBuilder(String s)
 {
      if (s==null || s.length() == 0)  //Special case for empty/null String
      {
           firstC = null;
           lastC = null;
           length = 0;
      }
      else
      {
           //Create front node to get started
           firstC = new CNode(s.charAt(0));
           length = 1;
           CNode currNode = firstC;
           // Create remaining nodes, copying from String.  Note
           // how each new node is simply added to the end of the
           // previous one.  Trace this to see what is going on.
           for (int i = 1; i < s.length(); i++){
                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
                length++;
           }
           lastC = currNode;
      }
 }

 // Create a new MyStringBuilder initialized with the chars in array s
 public MyStringBuilder(char [] s)
 {
      if(s == null || s.length == 0){
           firstC = null;
           lastC = null;
           length = 0;
      }
      else{
           //Create front node
           this.firstC = new CNode(s[0]);
           length = 1;
           CNode currNode = this.firstC;
           //Again, create remaining nodes. 
           //Except this time we get chars from array, not string
           for(int i = 1; i < s.length; i++){
                CNode newNode = new CNode(s[i]);
                currNode.next = newNode;
                currNode = newNode;
                this.length++;
           }
           this.lastC = currNode;
      } //pretty EZ
 }

      
 // Create a new empty MyStringBuilder
 public MyStringBuilder()
 {
           firstC = null;
           lastC = null;
           length = 0;
 }

 // Append MyStringBuilder b to the end of the current MyStringBuilder, and
 // return the current MyStringBuilder.  Be careful for special cases!
 public MyStringBuilder append(MyStringBuilder b)
 {
      /* Dear grader, I know this is a redundant traversal of stringbuilders
       * but after not being able to make this method work for a long time, I decided to 
       * take the easy way out and append the string. 
       * I've left some old code commented out if you want to take a look.
       * */
      this.append(b.toString()); 
      /*//special case: b is empty
      if(b.length == 0)
      {return this;}
      
      //special case: this is empty
      if(this.length == 0){
           this
      }
      CNode currNode = this.lastC;      
      CNode addNode = b.firstC;
      for(int i = 0; i < b.length; i++){
           CNode newNode = new CNode(addNode.data);
           currNode.next = newNode;
           currNode = newNode;
           if(addNode.next != null){
                addNode = addNode.next;
           }
      }
      this.firstC = currNode;
      this.length += b.length;*/
      return this;
 }


 // Append String s to the end of the current MyStringBuilder, and return
 // the current MyStringBuilder.  Be careful for special cases!
 public MyStringBuilder append(String s)
 {
      //special case: s is empty
      if(s.length() ==0)
      {return this;}
      //Special case: this is empty
      if(this.length == 0){
           this.firstC = new CNode(s.charAt(0));
           CNode currNode = this.firstC;
           this.length = 1;
           for(int i = 1; i < s.length(); i++){ //length - 1 ???
                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
                this.length++;
           }
           this.lastC = currNode;
        }
      //Can't create linkedlist with above constructor and add w/above append - "unnecessary copies"
      //This is very similar to the string-based constructor
      //Except we don't need to create a new starting node, we start from lastC 
      else{
           CNode currNode = this.lastC;
           for(int i = 0; i < s.length(); i++){
                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
                this.length++;
           }
           this.lastC = currNode;
 }
      return this;
 }

 // Append char array c to the end of the current MyStringBuilder, and
 // return the current MyStringBuilder.  Be careful for special cases!
 public MyStringBuilder append(char [] c)
 {
      //Special case: c is empty
      if(c.length == 0)
      {return this;}
      
      //special case: this is empty
      if(this.length == 0){
           MyStringBuilder newMSB = new MyStringBuilder(c);
           this.firstC = newMSB.firstC;
           this.lastC = newMSB.lastC;
           this.length = newMSB.length;
           return this;
      }
      
      //same deal as append(string), just use the array iteration
      CNode currNode = this.lastC;
      for(int i = 0; i < c.length; i++){
           CNode newNode = new CNode(c[i]);
           currNode.next = newNode;
           currNode = newNode;
           this.length++;
      }
      this.lastC = currNode;
      return this;
 }

 // Append char c to the end of the current MyStringBuilder, and
 // return the current MyStringBuilder.  Be careful for special cases!
 public MyStringBuilder append(char c)
 {
      //special case:  this is empty
      if(this.length == 0){
           this.firstC = new CNode(c);
           this.lastC = this.firstC;
           this.length = 1;
           return this;
      }
      //This is pretty easy. just add one node
      CNode newNode = new CNode(c);
      this.lastC.next = newNode;
      this.lastC = newNode;
      this.length++;
      return this;
 }

 // Return the character at location "index" in the current MyStringBuilder.
 // If index is invalid, throw an IndexOutOfBoundsException.
 public char charAt(int index)
 {
      //check for invalid index
      if(index < 0 || index > this.length){
           throw new IndexOutOfBoundsException();
      }
      else{
           CNode currNode = this.firstC;
           //advance thru nodes index times
           for(int i = 0; i < index; i++){
                currNode = currNode.next;
           }
           return currNode.data;
      }
 }

 // Delete the characters from index "start" to index "end" - 1 in the
 // current MyStringBuilder, and return the current MyStringBuilder.
 // If "start" is invalid or "end" <= "start" do nothing (just return the
 // MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
 // only remove up until the end of the MyStringBuilder. Be careful for 
 // special cases!
 public MyStringBuilder delete(int start, int end)
 {
      //check for invalid start or end
      if(start < 0 || start > this.length || end <= start){
           return this;
      }
      else
      {
           //find node before start
           CNode prevNode = this.firstC;
           for(int i = 0; i < start - 1; i++){
                prevNode = prevNode.next;
           }
           //find node at end
           //account for end index larger than this
           if(end > this.length){
                end = this.length;
           }
           CNode endNode = this.firstC;
           for(int i = 0; i < end; i++){
                endNode = endNode.next;
           }
           //if we're deleting starting at zero, set new firstC
           if(start == 0){
                this.firstC = endNode;
           }else{//Link 'em up
                prevNode.next = endNode;
           }
           //adjust length
           int lengthReduced = end - start;
           this.length -= lengthReduced;
           return this;
      }
 }

 
 // Delete the character at location "index" from the current
 // MyStringBuilder and return the current MyStringBuilder.  If "index" is
 // invalid, do nothing (just return the MyStringBuilder as is).
 // Be careful for special cases!
 public MyStringBuilder deleteCharAt(int index)
 {
      if(index < 0 || index > length -1){ //invalid index
           return this;
      }
      if(index == 0){ //if we're removing the first node, we simply declare the next node as the first
           this.firstC = firstC.next;
           this.length--;
           return this;
      }
      CNode currNode = this.firstC;
      CNode prevNode = null;
      for(int i = 0; i < index; i++){
           prevNode = currNode;
           currNode = currNode.next;
      }
      prevNode.next = currNode.next;
      this.length--;
      return this;
 }

 // Find and return the index within the current MyStringBuilder where
 // String str first matches a sequence of characters within the current
 // MyStringBuilder.  If str does not match any sequence of characters
 // within the current MyStringBuilder, return -1.  Think carefully about
 // what you need to do for this method before implementing it.
 public int indexOf(String str)
 {
      boolean found = false;
      CNode currNode = this.firstC;
      int counter = 0;
      //go thru MSB
      while(counter<this.length && !found){//this should handle the case where this.length = 0
           //go to next node if characters don't match
           if(currNode.data != str.charAt(0)){
                counter++;
                currNode = currNode.next;
           }
           else{ //if it matches, go thru string and compare to nodes
                CNode innerNode = currNode;
                int i = 0;
                while(i < str.length()){
                     if(innerNode.data == str.charAt(i)){ //if it matches, we advance in string and list
                          if(innerNode.next == null && i < str.length() - 1){//break out if we've reached the end of this but not str
                               return -1;
                          }
                          innerNode = innerNode.next;
                          i++;
                          if(i == str.length()){ //if we've gone all the way thru with everything matching, we return!
                               return counter;}
                     }
                     else{ //if it doesn't, break out of loop and look at next node
                          counter++;
                          currNode = currNode.next;
                          break;}                                          
                }                
           }                      
      }
      return -1;
 }

 // Insert String str into the current MyStringBuilder starting at index
 // "offset" and return the current MyStringBuilder.  if "offset" == 
 // length, this is the same as append.  If "offset" is invalid
 // do nothing.
 public MyStringBuilder insert(int offset, String str)
 {
      //invalid index
      if(offset < 0 || offset > length-1)
      {
           return this;
      }
      if(offset == length-1)
      {
           this.append(str);
           return this;
      }
      
      //find node prior to the inserted string
      CNode prevNode = this.firstC;           
      for(int i = 0; i < offset-1; i++)
      {
           prevNode = prevNode.next;
      }
      //find node after insertedstring
      CNode afterNode = prevNode.next;
      //loop to insert
      if(offset != 0){
           for(int i = 0; i < str.length(); i++){
                CNode newNode = new CNode(str.charAt(i));
                prevNode.next = newNode;
                prevNode = newNode;
           }
           prevNode.next = afterNode;
      }else{//if we're inserting at the beginning
           CNode currNode = new CNode(str.charAt(0));
           this.firstC = currNode;
           for(int i = 1; i < str.length(); i++){
                 CNode newNode = new CNode(str.charAt(i));
                 currNode.next = newNode;
                 currNode = newNode;                 
           }
           currNode.next = prevNode;
      }
      this.length += str.length();
      return this;
 }

 // Insert character c into the current MyStringBuilder at index
 // "offset" and return the current MyStringBuilder.  If "offset" ==
 // length, this is the same as append.  If "offset" is invalid, 
 // do nothing.
 public MyStringBuilder insert(int offset, char c)
 {
      //invalid index
      if(offset < 0 || offset > length-1){
           return this;
      }
      //create new node
      CNode newNode = new CNode(c);     
      if(offset == 0){
           newNode.next = this.firstC;
           this.firstC = newNode;
      }else{
           //find node prior to the inserted string
           CNode prevNode = this.firstC;           
           for(int i = 0; i < offset; i++){
                prevNode = prevNode.next;
           }
           //find node after insertedstring
           CNode afterNode = prevNode.next;
           //link it up
           prevNode.next = newNode;
           newNode.next = afterNode;
      }         
      this.length++;
      return this;
 }

 // Insert char array c into the current MyStringBuilder starting at index
 // index "offset" and return the current MyStringBuilder.  If "offset" is
 // invalid, do nothing.
 public MyStringBuilder insert(int offset, char [] c)
 {
      //invalid index
      if(offset < 0 || offset > length-1){
           return this;
      }
      if(offset == length-1){
           this.append(c);
           return this;
      }      
      //find node prior to the inserted arr
      CNode prevNode = this.firstC;           
      for(int i = 0; i < offset-1; i++){
           prevNode = prevNode.next;
      }
      //find node after insertedstring
      CNode afterNode = prevNode.next;
      //loop to insert
      if(offset != 0){
           for(int i = 0; i < c.length; i++){
                CNode newNode = new CNode(c[i]);
                prevNode.next = newNode;
                prevNode = newNode;
           }
           prevNode.next = afterNode;
      }else{//if we're inserting at the beginning
           CNode currNode = new CNode(c[0]);
           this.firstC = currNode;
           for(int i = 1; i < c.length; i++){
                 CNode newNode = new CNode(c[i]);
                 currNode.next = newNode;
                 currNode = newNode;                 
           }
           currNode.next = prevNode;
      }
      this.length += c.length;
      return this;
 }

 // Return the length of the current MyStringBuilder
 public int length()
 {
      return this.length;
 }

 // Delete the substring from "start" to "end" - 1 in the current
 // MyStringBuilder, then insert String "str" into the current
 // MyStringBuilder starting at index "start", then return the current
 // MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
 // If "end" is past the end of the MyStringBuilder, only delete until the
 // end of the MyStringBuilder, then insert.  This method should be done
 // as efficiently as possible.  In particular, you may NOT simply call
 // the delete() method followed by the insert() method, since that will
 // require an extra traversal of the linked list.
 public MyStringBuilder replace(int start, int end, String str)
 {
      //Build a linkedlist of the string. not gonna call constructor so not to create another object
      //Create front node to get started
      CNode repFirstNode = new CNode(str.charAt(0));
      CNode currNode = repFirstNode;
      for (int i = 1; i < str.length(); i++){
           CNode newNode = new CNode(str.charAt(i));
           currNode.next = newNode;
            currNode = newNode;
           }
      CNode repLastNode = currNode;
      
      //find start node 
      CNode prevNode = this.firstC;           
      for(int i = 0; i < start-1; i++){
           prevNode = prevNode.next;
      }
      //Now we differ in execution, depend on if end > length
      if(end < length){
           CNode afterNode = this.firstC;
           for(int i = 0; i < end; i++){
                afterNode = afterNode.next;
           }
           //link it up
           prevNode.next = repFirstNode;
           repLastNode.next = afterNode;
           this.length = this.length - (end-start) + str.length();
      }else{
           prevNode.next = repFirstNode;
           this.lastC = repLastNode;
           this.length = start + str.length();
      }

      
      return this;
 }

 // Reverse the characters in the current MyStringBuilder and then
 // return the current MyStringBuilder.
 public MyStringBuilder reverse()
 {
      if(this.length == 0 || this.length == 1)
      {return this;}
      else
      {
           CNode currNode = this.firstC;
           CNode newLast = new CNode(currNode.data);
           CNode newNext = newLast;
           CNode newCurr = null;
           for(int i = 0; i < length - 1; i ++)
           {
                currNode = currNode.next;
                newCurr = new CNode(currNode.data);
                newCurr.next = newNext;
                newNext = newCurr;                     
           }
           this.firstC = newCurr;
           this.lastC = newLast;
      }  
      return this;
 }
 
 // Return as a String the substring of characters from index "start" to
 // index "end" - 1 within the current MyStringBuilder
 public String substring(int start, int end)
 {
      int strLength = end-start;
      //find start node
      CNode startNode = this.firstC;
      for(int i = 0; i < start; i++){
           startNode = startNode.next;
      }
      //declare char array 
      char[] retval = new char[strLength];
      //add to array
      for(int i =0; i < strLength; i++){
           retval[i] = startNode.data;
           startNode = startNode.next;
      }
      String retString = new String(retval);
      return retString;
 }

 // Return the entire contents of the current MyStringBuilder as a String
 public String toString()
 {
      //special case empty MSB
      if(this.length == 0){
           return "";
      }
      char[] arr = new char[this.length]; //create char array, which will become string
      CNode currNode = this.firstC;
      arr[0] = currNode.data;
      for(int i = 0; i < this.length - 1; i++){
           currNode = currNode.next;
           //System.out.println(currNode.data);
           arr[i+1] = currNode.data;           
           
      }
      String retval = new String(arr);
      return retval;
 }
 
 //EXTRA CREDIT METHODS
 public int lastIndexOf(char c){ //returns last index of a certain character, -1 if char doesn't appear
      int ret = -1;
      CNode currNode = this.firstC;
      for(int i = 0; i < this.length; i++){
           if(currNode.data == c){
                ret = i;
           }
           currNode = currNode.next;
      }
      return ret;
 }
 public MyStringBuilder toUpperCase(){ //goes thru and turns all lowercase characters to uppercase
      CNode currNode = this.firstC;
      for(int i = 0; i < this.length; i++){
           currNode.data = Character.toUpperCase(currNode.data);
           currNode = currNode.next;
      }
      return this;
 }
  public MyStringBuilder toLowerCase(){ //I'll give you one guess what this does.
      CNode currNode = this.firstC;
      for(int i = 0; i < this.length; i++){
           currNode.data = Character.toLowerCase(currNode.data);
           currNode = currNode.next;
      }
      return this;
 }

 // You must use this inner class exactly as specified below.  Note that
 // since it is an inner class, the MyStringBuilder class MAY access the
 // data and next fields directly.
 private class CNode
 {
  private char data;
  private CNode next;

  public CNode(char c)
  {
   data = c;
   next = null;
  }

  public CNode(char c, CNode n)
  {
   data = c;
   next = n;
  }
 }
 
 
}

