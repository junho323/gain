let moreNum = 1;
$(".list li:nth-child(n + " + (moreNum + 1) + ")").addClass("is-hidden");
$(".more2").on("click", function () {
  $(".list li.is-hidden").slice(0, moreNum).removeClass("is-hidden");
  if ($(".list li.is-hidden").length == 0) {
    $(".more").fadeOut();
  }
});