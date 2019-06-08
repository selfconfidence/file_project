
function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}
var jwt = '';
var vue = new Vue({
    el: '#app',
    data: {
            deleteFileLocal:'',
            jwt:'',
            copyFiles:{
                resourceFileLocal:'',
                destFileLocal:''
            }

    },
  created:() =>{
        //去访问接口查看是否是登陆状态.
        //仿制head头信息
      jwt =  GetQueryString("auth");
    },
    methods:{
        deleteFile(){
            this.$confirm('此操作将备份该路径所有文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除逻辑操作
                axios.post('/file/deleteFiles',{"fileLocal":this.deleteFileLocal,
                },{headers: {'AUTH': jwt}}).then( response => {
                    if (response.data.flag) {
                        this.messageTitle("success",response.data.message);
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }else {

                        this.messageTitle("error",response.data.message);
                        this.$message({
                            type: 'error',
                            message: '删除失败!'
                        });
                    }

                })

            }).catch((e) => {

                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        copyFile(){
                //文件备份操作
            this.$confirm('此操作备份文件下的所有文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除逻辑操作
                axios.post('/file/copyFiles',this.copyFiles,{headers: {'AUTH': jwt}}).then( response => {
                    if (response.data.flag) {
                        this.messageTitle("success",response.data.message);
                        this.$message({
                            type: 'success',
                            message: response.data.message
                        });
                    }else {

                        this.messageTitle("error",response.data.message);
                        this.$message({
                            type: 'error',
                            message: response.data.message
                        });
                    }

                })

            }).catch((e) => {

                this.$message({
                    type: 'info',
                    message: '已取消备份~'
                });
            });

        },
       messageTitle(type,message){
           const h = this.$createElement;
           this.$notify({
               title: '删除提示!',
               message: h('i', { style: 'color: blue'}, message),
               type: type
           });
       }
    }
})