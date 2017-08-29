package com.feige;

public class Problem8 {

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
      * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        int middle = left;
        while (array[left] >= array[right]) {
            if(right - left == 1) {
                middle = right;
                break;
            }
            middle = (left + right)/2;
            //如果下标为left，right和middle指向的三个数字相等，则只能顺序查找
            if(array[left] == array[right] && array[right] == array[middle]) {
                int res = array[left];
                for(int i = left + 1;i<=right;++i) {
                    if(res > array[i]) {
                        res = array[i];
                    }
                }
                array[middle] = res;
            }
            if(array[middle] >= array[left]) {
                left = middle;
            }else if(array[middle] <= array[right]) {
                right = middle;
            }
        }
        return array[middle];
    }

}
