describe("Clicking the run button", function() {

 beforeEach(function() {
      var html = "<input id=\"user1text\" value=\"a\"/><input id=\"user2text\" value=\"b\"" + 
                 "<button id=\"run-button\" value=\"run\"/><div id=\"results\"/>";
      setFixtures(html);
    });

  it("should have user1text and user2text set to a and b", function() {
      console.log($("#user1text"));
      expect($("#user1text").val()).toBe("a");
      expect($("#user2text").val()).toBe("b");
    });

  describe("when the run button is clicked with two valid twitter users", function() {
    beforeEach(function() {
      var fakeData = "Success return data";
      spyOn($, "ajax").andCallFake(function(params) {
        params.success(fakeData);
      });
    });

    it("should return results with a results div and no raptor div", function() {
      $("#run-button").trigger("click");
      expect($("#results")).toExist();
      console.log($("#results"));
      expect($("#raptor")).not.toExist();
    });
  });

 describe("when the run button is clicked with invalid twitter users", function() {

   //getting problems with recursive call to the raptorize function
   //must sort out later.
  })

});
