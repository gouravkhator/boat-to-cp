import java.util.ArrayList;

class CanPlaceFlowers {
  // Problem Question: https://leetcode.com/problems/can-place-flowers/
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    /*
    Logic:

    If n is 0, then always we can place 0 flowers.
    If total flower pots is 1, then if that has value 0, we can place n flowers.

    Or else, save indices to indices array list which have 0 on both of their sides.

    If indices list is empty, just return false. (as here we have n as atleast 1)
    Loop through indices, and check whichever is not conseuctive just don't count them.

    We are saving last counted index and checking future index with last counted one.
    Lastly, if count of valid indices >= n then we say the n flowers can be placed, else not.
    */
    if(n==0) return true;
    
    int len = flowerbed.length;
    if(len == 1){
      return (flowerbed[0] == 0); 
    }
    
    ArrayList<Integer> indices = new ArrayList<>();
    
    int i=0;
    if(flowerbed[0] == 0 && flowerbed[1] == 0){
      indices.add(0);
    }
    
    for(i=1; i<=len - 2; i++){
      if(flowerbed[i] == 0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0){
        indices.add(i);
      }
    }
      
    if(flowerbed[len - 1] == 0 && flowerbed[len - 2] == 0){
      indices.add(len - 1);
    }
    
    int indicesLen = indices.size(), count=1;

    if(indicesLen == 0){
      return false;
    }

    int savedIndex = indices.get(0);

    for(i=1; i<indicesLen; i++){
      int currentIndex = indices.get(i);
      int previousIndex = savedIndex;
      
      if(currentIndex != (previousIndex + 1)){
        count++;
        savedIndex = currentIndex;
      }
    }
    
    return count >= n;
  }
}
