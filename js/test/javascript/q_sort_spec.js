require("spec_helper.js");
require("../../public/javascripts/q_sort.js");

Screw.Unit(function(){
  describe("QSort", function(){
    it("sorts an array", function(){
      var arr = [ 1, 5, 3, 8, 4, 8, 19 ];

      expect(arr.qsort()).to(equal, [ 1, 3, 4, 5, 8, 8, 19 ]);
    });

    it("handles empty array", function() {
    
      expect([].qsort()).to(equal, []);
    });

    it("handles objects with custom comparison", function() {
      var arr= [
        { num : 1 },
        { num : 5 },
        { num : 3 },
        { num : 8 },
        { num : -1 },
      ];

      arr.qsort(function(a, b) {
        return a.num - b.num;
      });

      var nums = jQuery.map(arr, function(o) { return o.num; });

      expect(nums).to(equal, [ -1, 1, 3, 5, 8 ]);
    });
  });
});
