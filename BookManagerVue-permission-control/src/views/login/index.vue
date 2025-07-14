<template>
  <div class="login-container">
    <!-- 背景装饰元素 -->
    <div class="tech-pattern"></div>

    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
      <!-- 标题 -->
      <div class="title-container">
        <h3 class="title">
          <span class="neon-text">图书管理系统</span>
          <span class="subtitle">- 登录界面</span>
        </h3>
      </div>

      <!-- 用户名 -->
      <el-form-item prop="username" class="form-item-tech">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          class="yuan"
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入用户名"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <!-- 密码 -->
      <el-form-item prop="password" class="form-item-tech password-item">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          class="yuan"
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="请输入密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <!-- 权限 -->
      <el-form-item prop="authority" class="form-item-tech">
        <span class="svg-container">
          <svg-icon icon-class="lock" />
        </span>
        <el-select v-model="loginForm.isadmin" placeholder="请选择权限" class="authority-select">
          <el-option :key="1" label="管理员" :value="1"></el-option>
          <el-option :key="0" label="读者" :value="0"></el-option>
        </el-select>
      </el-form-item>

      <!-- 登录按钮 -->
      <div class="button-group">
        <el-button :loading="loading" type="primary" class="tech-btn login-btn" @click.native.prevent="handleLogin">
          <span>登录</span>
          <span class="btn-glow"></span>
        </el-button>
        <el-button :loading="loading" type="success" class="tech-btn register-btn" @click.native.prevent="handleRegister">
          <span>注册</span>
          <span class="btn-glow"></span>
        </el-button>
      </div>

      <!-- 提示 -->
      <div class="tips-container">
        <div class="tips">
          <span>管理员: admin / admin</span>
        </div>
      </div>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入用户名'))
      }
      callback()
    }
    const validatePassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入密码'))
      }
      callback()
    }
    return {
      loginForm: {
        username: 'admin',
        password: 'admin',
        isadmin: 1
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  methods: {
    showPwd() {
      this.passwordType = this.passwordType === 'password' ? '' : 'password'
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: '/' })
            this.loading = false
          }).catch((message) => {
            this.$message.error(message)
            this.loading = false
          })
        }
      })
    },
    handleRegister() {
      this.$router.push({ path: '/register' })
    }
  }
}
</script>

<style lang="scss">
/* 基础科技风格变量 */
$neon-blue: #00f3ff;
$neon-purple: #bf00ff;
$neon-pink: #ff00c8;
$bg-dark: #0f1221;
$glass-bg: rgba(15, 18, 33, 0.7);
$border-glow: 0 0 5px $neon-blue, 0 0 10px $neon-blue;

/* 全局样式调整 */
@supports (-webkit-mask: none) and (not (cater-color: $neon-blue)) {
  .login-container .el-input input {
    color: #fff;
  }
}

/* 背景与装饰 */
.login-container {
  min-height: 100vh;
  width: 100%;
  position: relative;
  overflow: hidden;

  /* 科技感书籍背景图 */
  background-image: url("C:/Users/张重锌/Desktop/生成科技感书籍图片.png");
  background-size: cover;
  background-position: center;
  background-attachment: fixed;

  /* 背景叠加层增强可读性 */
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(15, 18, 33, 0.9), rgba(15, 18, 33, 0.7));
  }

  .tech-pattern {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-image:
      linear-gradient(rgba(0, 243, 255, 0.1) 1px, transparent 1px),
      linear-gradient(90deg, rgba(0, 243, 255, 0.1) 1px, transparent 1px);
    background-size: 50px 50px;
    z-index: 1;
    pointer-events: none;
  }

  .login-form {
    position: relative;
    z-index: 2;
    width: 520px;
    max-width: 100%;
    padding: 120px 35px 50px;
    margin: 0 auto;

    /* 玻璃态表单容器 */
    backdrop-filter: blur(10px);
    background: $glass-bg;
    border: 1px solid rgba(0, 243, 255, 0.2);
    border-radius: 12px;
    box-shadow: 0 0 30px rgba(0, 243, 255, 0.15);
  }

  .title-container {
    text-align: center;
    margin-bottom: 40px;

    .title {
      font-size: 28px;
      font-weight: 700;

      .neon-text {
        color: $neon-blue;
        text-shadow: 0 0 5px $neon-blue, 0 0 10px $neon-blue, 0 0 20px $neon-blue;
      }

      .subtitle {
        color: #ccc;
        margin-left: 10px;
        font-size: 20px;
      }
    }
  }

  .svg-container {
    color: $neon-blue;
    width: 30px;
    display: inline-block;
    transition: all 0.3s;
    vertical-align: middle;
  }

  .form-item-tech {
    position: relative;
    margin-bottom: 20px;
    border: 1px solid rgba(0, 243, 255, 0.3);
    border-radius: 8px;
    transition: all 0.3s;
    padding: 0 10px;

    &:focus-within {
      border-color: $neon-blue;
      box-shadow: $border-glow;
    }

    .el-input {
      width: calc(100% - 40px);
      display: inline-block;
      vertical-align: middle;

      input {
        color: #fff;
        background: transparent;
        padding: 15px 10px;
        border: none;
        outline: none;
        width: 100%;
      }
    }
  }

  /* 密码框特殊样式 */
  .password-item {
    .el-input {
      width: calc(100% - 70px); /* 为眼睛图标预留额外空间 */
    }
  }

  /* 眼睛图标样式（嵌入密码框内） */
  .show-pwd {
    color: $neon-blue;
    position: absolute;
    right: 15px;
    top: 50%;
    transform: translateY(-50%); /* 垂直居中 */
    cursor: pointer;
    transition: all 0.3s;
    width: 24px;
    height: 24px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    z-index: 10;

    &:hover {
      color: $neon-pink;
      transform: translateY(-50%) scale(1.1); /* 悬停放大效果 */
    }

    svg {
      width: 18px;
      height: 18px;
    }
  }

  .authority-select {
    width: calc(100% - 40px);
    color: #fff;
    display: inline-block;
    vertical-align: middle;

    .el-input__inner {
      background: transparent;
      border: none;
      color: #fff;
      padding: 15px 10px;
    }

    .el-select-dropdown {
      background: $bg-dark;
      border: 1px solid $neon-blue;

      .el-select-dropdown__item {
        color: #ccc;

        &:hover {
          background: rgba(0, 243, 255, 0.1);
          color: $neon-blue;
        }

        &.selected {
          background: rgba(0, 243, 255, 0.2);
          color: $neon-blue;
        }
      }
    }
  }

  .button-group {
    display: flex;
    gap: 15px;
    margin: 40px 0 30px;
  }

  .tech-btn {
    flex: 1;
    height: 50px;
    font-size: 16px;
    font-weight: 500;
    border: none;
    border-radius: 8px;
    position: relative;
    overflow: hidden;
    transition: all 0.3s;

    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: -100%;
      width: 100%;
      height: 100%;
      background: linear-gradient(90deg, transparent, rgba(255,255,255,0.2), transparent);
      transition: all 0.6s;
    }

    &:hover::before {
      left: 100%;
    }
  }

  .login-btn {
    background: linear-gradient(135deg, #0066cc, $neon-blue);
    box-shadow: 0 0 15px rgba(0, 243, 255, 0.4);

    &:hover {
      box-shadow: 0 0 20px rgba(0, 243, 255, 0.6);
      transform: translateY(-2px);
    }
  }

  .register-btn {
    background: linear-gradient(135deg, #6600cc, $neon-purple);
    box-shadow: 0 0 15px rgba(191, 0, 255, 0.4);

    &:hover {
      box-shadow: 0 0 20px rgba(191, 0, 255, 0.6);
      transform: translateY(-2px);
    }
  }

  .tips-container {
    text-align: center;
    margin-top: 30px;

    .tips {
      color: #aaa;
      font-size: 14px;
      margin-bottom: 8px;

      span {
        color: #ddd;
      }
    }
  }
}
</style>
