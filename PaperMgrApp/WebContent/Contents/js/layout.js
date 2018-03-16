$(function () {
    setLayoutSize();
})
function setLayoutSize() {
    $('body').css('width',innerWidth);
    $('.container').css('height', (innerHeight - 90) + 'px');
    $('.main').css('width', (innerWidth - 205) + 'px');
    $('.main .layui-tab-content').css('height', (innerHeight - 90 - 60) + 'px');
}