$(document).ready(function() {

  $("#run-button").live("click", function() {
    $('#results').html("Working on your request .....").show();
    var user1 = $("#user1text").val();
    var user2 = $("#user2text").val();
    var urlparams = "user1=" + user1 + "&user2=" + user2;   

    $.ajax({
    type: "POST",
    url: "/incommon",
    data: urlparams,
    success: function(msg){
     console.log("here!")
     $("#results").html(msg);
     console.log($("#results"));
     $('#raptor').raptorize();
     $('#raptor').trigger("click");
    }
 });
     
  });


});
