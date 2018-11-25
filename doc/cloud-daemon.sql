SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name`    varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name`  varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `blob_data`     blob,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name`    varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `calendar`      blob       NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name`      varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name`    varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group`   varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `cron_expression` varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `time_zone_id`    varchar(80) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers`
VALUES ('cloud-scheduler', 'trigger测试', 'Test Group', '* 59 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name`        varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `entry_id`          varchar(95) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `trigger_name`      varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `trigger_group`     varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `instance_name`     varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `fired_time`        bigint(13) NOT NULL,
  `sched_time`        bigint(13) NOT NULL,
  `priority`          int(11)    NOT NULL,
  `state`             varchar(16) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `job_name`          varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  `job_group`         varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_nonconcurrent`  varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE,
  INDEX `idx_qrtz_ft_trig_inst_name`(`sched_name`, `instance_name`) USING BTREE,
  INDEX `idx_qrtz_ft_inst_job_req_rcvry`(`sched_name`, `instance_name`, `requests_recovery`) USING BTREE,
  INDEX `idx_qrtz_ft_j_g`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  INDEX `idx_qrtz_ft_jg`(`sched_name`, `job_group`) USING BTREE,
  INDEX `idx_qrtz_ft_t_g`(`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `idx_qrtz_ft_tg`(`sched_name`, `trigger_group`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name`        varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `job_name`          varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `job_group`         varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `description`       varchar(250) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci DEFAULT NULL,
  `job_class_name`    varchar(250) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `is_durable`        varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `is_nonconcurrent`  varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `is_update_data`    varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `job_data`          blob,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE,
  INDEX `idx_qrtz_j_req_recovery`(`sched_name`, `requests_recovery`) USING BTREE,
  INDEX `idx_qrtz_j_grp`(`sched_name`, `job_group`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details`
VALUES ('cloud-scheduler',
        '测试',
        'Test Group',
        'x',
        'com.github.cloud.daemon.biz.job.JobTest',
        '0',
        '0',
        '0',
        '0',
        0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `lock_name`  varchar(40) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks`
VALUES ('cloud-scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks`
VALUES ('cloud-scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name`    varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name`        varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `instance_name`     varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval`  bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state`
VALUES ('cloud-scheduler', 'DESKTOP-KG49AN91543157243032', 1543157364203, 10000);
INSERT INTO `qrtz_scheduler_state`
VALUES ('quartzScheduler', 'DESKTOP-KG49AN91542977717811', 1542978643222, 10000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name`      varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci   NOT NULL,
  `trigger_name`    varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci   NOT NULL,
  `trigger_group`   varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci   NOT NULL,
  `repeat_count`    bigint(7)  NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name`    varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_name`  varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci NOT NULL,
  `str_prop_1`    varchar(512) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     DEFAULT NULL,
  `str_prop_2`    varchar(512) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     DEFAULT NULL,
  `str_prop_3`    varchar(512) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     DEFAULT NULL,
  `int_prop_1`    int(11)        DEFAULT NULL,
  `int_prop_2`    int(11)        DEFAULT NULL,
  `long_prop_1`   bigint(20)     DEFAULT NULL,
  `long_prop_2`   bigint(20)     DEFAULT NULL,
  `dec_prop_1`    decimal(13, 4) DEFAULT NULL,
  `dec_prop_2`    decimal(13, 4) DEFAULT NULL,
  `bool_prop_1`   varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     DEFAULT NULL,
  `bool_prop_2`   varchar(1) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci     DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name`     varchar(120) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `trigger_name`   varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `trigger_group`  varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `job_name`       varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `job_group`      varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `description`    varchar(250) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci   DEFAULT NULL,
  `next_fire_time` bigint(13)  DEFAULT NULL,
  `prev_fire_time` bigint(13)  DEFAULT NULL,
  `priority`       int(11)     DEFAULT NULL,
  `trigger_state`  varchar(16) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `trigger_type`   varchar(8) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci  NOT NULL,
  `start_time`     bigint(13) NOT NULL,
  `end_time`       bigint(13)  DEFAULT NULL,
  `calendar_name`  varchar(200) CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci   DEFAULT NULL,
  `misfire_instr`  smallint(2) DEFAULT NULL,
  `job_data`       blob,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `idx_qrtz_t_j`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  INDEX `idx_qrtz_t_jg`(`sched_name`, `job_group`) USING BTREE,
  INDEX `idx_qrtz_t_c`(`sched_name`, `calendar_name`) USING BTREE,
  INDEX `idx_qrtz_t_g`(`sched_name`, `trigger_group`) USING BTREE,
  INDEX `idx_qrtz_t_state`(`sched_name`, `trigger_state`) USING BTREE,
  INDEX `idx_qrtz_t_n_state`(`sched_name`, `trigger_name`, `trigger_group`, `trigger_state`) USING BTREE,
  INDEX `idx_qrtz_t_n_g_state`(`sched_name`, `trigger_group`, `trigger_state`) USING BTREE,
  INDEX `idx_qrtz_t_next_fire_time`(`sched_name`, `next_fire_time`) USING BTREE,
  INDEX `idx_qrtz_t_nft_st`(`sched_name`, `trigger_state`, `next_fire_time`) USING BTREE,
  INDEX `idx_qrtz_t_nft_misfire`(`sched_name`, `misfire_instr`, `next_fire_time`) USING BTREE,
  INDEX `idx_qrtz_t_nft_st_misfire`(`sched_name`, `misfire_instr`, `next_fire_time`, `trigger_state`) USING BTREE,
  INDEX `idx_qrtz_t_nft_st_misfire_grp`(`sched_name`, `misfire_instr`, `next_fire_time`, `trigger_group`, `trigger_state`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers`
VALUES ('cloud-scheduler',
        'trigger测试',
        'Test Group',
        '测试',
        'Test Group',
        NULL,
        1543157940000,
        1543154399000,
        5,
        'WAITING',
        'CRON',
        1543152237000,
        0,
        NULL,
        0,
        '');

SET FOREIGN_KEY_CHECKS = 1;
