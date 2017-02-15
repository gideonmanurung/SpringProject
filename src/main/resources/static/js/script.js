/**
 * 
 */$(document).ready(function() {
  $('.node').click(function(event) {
    $(event.target).toggleClass('expanded');
  });
});