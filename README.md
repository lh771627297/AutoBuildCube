Rest Api 构建cube
curl -X PUT --user ADMIN:KYLIN -H 'Content-Type: application/json' -d '{"startTime":'1504310400000', "endTime":'1504396800000', "buildType":"BUILD"}'
http://172.26.5.200:7070/kylin/api/cubes/cube_svc_session_20170903/build

实际的时间你需要增加8小时在转换为UNIX时间戳
2017-09-01 00:00:00   实际上应该用  2017-09-01 00:08:00 来转换UNIX时间戳