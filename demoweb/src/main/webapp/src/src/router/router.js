 /* explain说明相关 @1 abouts:关于我们 */ 
 const explain=[
    { path: '/abouts', name: 'mj', meta:{ title:"关于我们" }, component: resolve => require(['../components/explain/abouts.vue'], resolve) },
 ]
  /* homeCoin @1 home_coin:币主页 */ 
 const homeCoin=[
    { path: '/home_coin', name: 'login', meta:{ title:"币主页" }, component: resolve => require(['../components/homeCoin/home_coin.vue'], resolve) },
 ]
 /* myCoin 我的币名片 @1 my_coin：我的币名片 */
 const myCoin=[
    { path: '/my_coin', name: 'my_coin', meta:{ title:"我的币名片" }, component: resolve => require(['../components/myCoin/my_coin.vue'], resolve) },
 ];
  /* rankingsCoin币排行榜 @1 rankings：币排行榜 */
  const rankingsCoin=[
    { path: '/rankings', name: 'rankings', meta:{ title:"币排行榜" }, component: resolve => require(['../components/rankingsCoin/rankings.vue'], resolve) },
 ];

 /* share微信分享 @1 share_coin：币名片 */
 const share=[
    { path: '/share_coin', name: 'share_coin', meta:{ title:"币名片" }, component: resolve => require(['../components/share/share_coin.vue'], resolve) },
 ];
export default [
    ...explain,
    ...homeCoin,
    ...myCoin,
    ...rankingsCoin,
    ...share
];