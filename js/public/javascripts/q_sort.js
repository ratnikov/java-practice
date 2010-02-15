var QSort = function(arr, compareTo) {
  if (typeof compareTo === 'undefined') {
    this.compare = function(a, b) {
      return a - b;
    };
  } else {
    this.compare = compareTo;
  }
  
  this.arr = arr;
};

QSort.prototype = {
  swap: function(i, j) {
    var val = this.arr[i];
    this.arr[i] = this.arr[j];
    this.arr[j] = val;

    return this;
  },

  partition: function(left, right, pivotIndex) {
    var pivot = this.arr[pivotIndex];

    this.swap(right, pivotIndex);

    var storeIndex = left;
    for (var i = left; i < right; i++) {
      if (this.compare(this.arr[i], pivot) < 0) {
        this.swap(storeIndex, i);
        storeIndex += 1;
      }
    }

    // put the pivot where it should be
    this.swap(storeIndex, right);

    // return the location of the pivotal index
    return storeIndex;
  },

  // Sorts the sub-array
  sort: function(left, right) {
    if (right > left) {
      // grab the last element as the pivot
      var pivotIndex = this.partition(left, right, right);  

      // sort the left part
      if (pivotIndex !== left) {
        this.sort(left, pivotIndex - 1);
      }

      // sort the right part
      if (pivotIndex !== right) {
        this.sort(pivotIndex + 1, right);
      }
    } else {
      // if left = right, we're sorted. Nothing to do
    }

    return this;
  }
};

Array.prototype.qsort = function(compareTo) {
  if (typeof(compareTo) === 'undefined') {
    compareTo = function(a, b) {
      return a - b;
    };
  }

  (new QSort(this, compareTo)).sort(0, this.length - 1);

  return this;
};
