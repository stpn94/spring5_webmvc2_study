SELECT database(),user();

desc member;

select *
  from member;
 
 
select * 
  from member
 where regdate between '2021-05-24' and '2021-05-26' order by regdate desc;



delete
  from member
 where EMAIL='test2@test2.co.kr';
 