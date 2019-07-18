var my_time;
$(window).ready(function() {
  pageScroll();
  $("#contain").mouseover(function() {
    clearTimeout(my_time);
  }).mouseout(function() {
    pageScroll();
  });
});

function pageScroll() {  
  var objDiv = document.getElementById("contain");
  objDiv.scrollTop = objDiv.scrollTop + 1;  
  if (objDiv.scrollTop >= objDiv.scrollHeight - 480) {
    objDiv.scrollTop = 0;
  }
  my_time = setTimeout('pageScroll()', 25);
}