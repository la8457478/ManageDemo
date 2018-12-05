<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="dataForm.name" placeholder="姓名"></el-input>
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password" :class="{ 'is-required': !dataForm.id }">
        <el-input v-model="dataForm.password" type="password" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="comfirmPassword" :class="{ 'is-required': !dataForm.id }">
        <el-input v-model="dataForm.comfirmPassword" type="password" placeholder="确认密码"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item label="微信" prop="wechat">
        <el-input v-model="dataForm.wechat" placeholder="微信"></el-input>
      </el-form-item>
      <el-form-item label="qq" prop="qq">
        <el-input v-model="dataForm.qq" placeholder="qq"></el-input>
      </el-form-item>
      <el-form-item label="推荐人姓名" prop="refereeName">
        <el-input v-model="dataForm.refereeName" placeholder="推荐人姓名"></el-input>
      </el-form-item>
      <el-form-item label="推荐人手机" prop="refereeMobile">
        <el-input v-model="dataForm.refereeMobile" placeholder="推荐人手机"></el-input>
      </el-form-item>
      <el-form-item label="推荐人微信" prop="refereeWechat">
        <el-input v-model="dataForm.refereeWechat" placeholder="推荐人微信"></el-input>
      </el-form-item>
      <el-form-item label="推荐人QQ" prop="refereeQq">
        <el-input v-model="dataForm.refereeQq" placeholder="推荐人QQ"></el-input>
      </el-form-item>

      <el-form-item label="上级用户" prop="parentId">
        <el-select v-model="dataForm.parentId" placeholder="请选择">
          <el-option
            v-for="user in userList"
            :key="user.userId"
            :label="user.username"
            :value="user.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="会员类型" prop="memberType">
        <el-radio-group v-model="dataForm.memberType">
          <el-radio :label="1">普通会员</el-radio>
          <el-radio :label="2">超级会员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="角色" size="mini" prop="roleId">
        <el-radio-group v-model="dataForm.roleId">
          <el-radio v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{role.roleName}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <!--<el-form-item label="角色" size="mini" prop="roleIdList">-->
      <!--<el-checkbox-group v-model="dataForm.roleIdList" :min="1"-->
      <!--:max="1">-->
      <!--<el-checkbox v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{ role.roleName }}-->
      <!--</el-checkbox>-->
      <!--</el-checkbox-group>-->
      <!--</el-form-item>-->
      <!--<el-form-item label="状态" size="mini" prop="status">-->
      <!--<el-radio-group v-model="dataForm.status">-->
      <!--<el-radio :label="0">禁用</el-radio>-->
      <!--<el-radio :label="1">正常</el-radio>-->
      <!--</el-radio-group>-->
      <!--</el-form-item>-->

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {isEmail, isMobile} from '@/utils/validate'

  export default {
    data() {
      var validatePassword = (rule, value, callback) => {
        if (!this.dataForm.id && !/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }
      var validateComfirmPassword = (rule, value, callback) => {
        if (!this.dataForm.id && !/\S/.test(value)) {
          callback(new Error('确认密码不能为空'))
        } else if (this.dataForm.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        } else {
          callback()
        }
      }
      var validateEmail = (rule, value, callback) => {
        if (!isEmail(value)) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }
      var validateMobile = (rule, value, callback) => {
        if (!isMobile(value)) {
          callback(new Error('手机号格式错误'))
        } else {
          callback()
        }
      }
      return {
        visible: false,
        roleList: [],
        userList:[],
        dataForm: {
          id: 0,
          userName: '',
          password: '',
          comfirmPassword: '',
          salt: '',
          email: '',
          refereeQq: '',
          refereeName: '',
          refereeMobile: '',
          refereeWechat: '',
          mobile: '',
          name:'',
          memberType:'',
          parentId: "",
          wechat:'',
          roleId: '',
          qq:'',
          roleIdList: [],
          status: 1
        },
        dataRule: {
          userName: [
            {required: true, message: '用户名不能为空', trigger: 'blur'}
          ],
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          comfirmPassword: [
            {validator: validateComfirmPassword, trigger: 'blur'}
          ],
          // ,
          // email: [
          //   {required: true, message: '邮箱不能为空', trigger: 'blur'},
          //   {validator: validateEmail, trigger: 'blur'}
          // ],
          mobile: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {validator: validateMobile, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/sys/user/select'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.userList = data && data.code === 0 ? data.list : []
        })

        this.$http({
          url: this.$http.adornUrl('/sys/role/select'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.roleList = data && data.code === 0 ? data.list : []
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/sys/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.user.username
                this.dataForm.salt = data.user.salt
                this.dataForm.email = data.user.email
                this.dataForm.mobile = data.user.mobile
                this.dataForm.qq = data.user.qq
                this.dataForm.wechat = data.user.wechat
            this.dataForm.parentId = data.user.parentId
            this.dataForm.name = data.user.name
            this.dataForm.roleId = data.user.roleId
            this.dataForm.refereeQq = data.user.refereeQq
            this.dataForm.refereeName = data.user.refereeName
            this.dataForm.refereeMobile = data.user.refereeMobile
            this.dataForm.refereeWechat = data.user.refereeWechat
            this.dataForm.memberType = data.user.memberType
            // this.dataForm.roleIdList = data.user.roleIdList
                this.dataForm.status = data.user.status
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'userId': this.dataForm.id || undefined,
                'username': this.dataForm.userName,
                'password': this.dataForm.password,
                'salt': this.dataForm.salt,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'status': this.dataForm.status,
                'parentId': this.dataForm.parentId,
                'wechat': this.dataForm.wechat,
                'qq': this.dataForm.qq,
                'name': this.dataForm.name,
                'roleId': this.dataForm.roleId,
                'refereeQq': this.dataForm.refereeQq,
                'refereeName': this.dataForm.refereeName,
                'refereeMobile': this.dataForm.refereeMobile,
                'refereeWechat': this.dataForm.refereeWechat,
                'memberType':  this.dataForm.memberType
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
