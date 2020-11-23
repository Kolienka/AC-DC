pragma solidity ^0.4;

contract Bubblesort {
    
    function sort(uint[] memory data) public returns(uint[] memory) {
       bubblesort(data);
       return data;
    }
    
    function bubblesort(uint[] memory arr) internal{
        bool sorted = true;
        for(uint i=0; i < arr.length - 1; i++) {
            if(arr[uint(i)] > arr[uint(i + 1)]) {
                (arr[uint(i)], arr[uint(i + 1)]) = (arr[uint(i + 1)], arr[uint(i)]);
                sorted = false;
            }
        }
        
        if(!sorted) {
            bubblesort(arr);
        }
    }
}
