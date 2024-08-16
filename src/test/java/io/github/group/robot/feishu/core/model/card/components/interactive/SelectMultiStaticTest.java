package io.github.group.robot.feishu.core.model.card.components.interactive;

import io.github.group.robot.feishu.core.FeishuRobotClient;
import io.github.group.robot.feishu.core.FeishuRobotResponse;
import io.github.group.robot.feishu.core.constants.ActionType;
import io.github.group.robot.feishu.core.constants.ButtonType;
import io.github.group.robot.feishu.core.constants.FlexMode;
import io.github.group.robot.feishu.core.constants.HorizontalSpacing;
import io.github.group.robot.feishu.core.constants.IconTag;
import io.github.group.robot.feishu.core.constants.SelectType;
import io.github.group.robot.feishu.core.constants.VerticalAlign;
import io.github.group.robot.feishu.core.model.CardV2Message;
import io.github.group.robot.feishu.core.model.card.CardBody;
import io.github.group.robot.feishu.core.model.card.CardHeader;
import io.github.group.robot.feishu.core.model.card.components.CardTitle;
import io.github.group.robot.feishu.core.model.card.components.containers.ColumnSet;
import io.github.group.robot.feishu.core.model.card.components.containers.FormContainer;
import io.github.group.robot.feishu.core.model.card.components.containers.ele.Column;
import io.github.group.robot.feishu.core.model.card.components.content.Title;
import io.github.group.robot.feishu.core.model.card.components.ele.IconEl;
import io.github.group.robot.feishu.core.model.card.components.interactive.ele.SelectOption;
import org.junit.Test;

import java.util.ArrayList;

public class SelectMultiStaticTest {

    /**
     * select multi static 示例
     */
    @Test
    public void test() {
        FormContainer formContainer = FormContainer.builder()
                .setName("Form_lrztw8x2")
                .addElement(ColumnSet.builder()
                        .setFlexMode(FlexMode.NONE)
                        .setHorizontalSpacing(HorizontalSpacing.DEFAULT)
                        .setBackgroundStyle("default")
                        .addColumn(Column.builder()
                                .addElement(SelectMultiStatic.builder()
                                        .setType(SelectType.DEFAULT)
                                        .setName("multi_select_departments")
                                        .setPlaceholder("默认提示文本")
                                        .setWidth("fill")
                                        .setRequired(true)
                                        .setDisabled(false)
                                        .setSelectedValues(new ArrayList<>())
                                        .addOption(SelectOption.of("选项1", "selectDemo1")
                                                .setIcon(IconEl.builder()
                                                        .setTag(IconTag.STANDARD_ICON)
                                                        .setToken("chat-forbidden_outlined")
                                                        .setColor("orange")
                                                        .build()))
                                        .addOption(SelectOption.of("选项2", "selectDemo2")
                                                .setIcon(IconEl.builder()
                                                        .setTag(IconTag.STANDARD_ICON)
                                                        .setToken("chat-forbidden_outlined")
                                                        .setColor("orange")
                                                        .build()))
                                        .build())
                                .setWidth("weighted")
                                .setWeight(1)
                                .build())
                        .build())
                .addElement(ColumnSet.builder()
                        .setFlexMode(FlexMode.NONE)
                        .setHorizontalSpacing(HorizontalSpacing.DEFAULT)
                        .setBackgroundStyle("default")
                        .addColumn(Column.builder()
                                .setWidth("auto")
                                .setVerticalAlign(VerticalAlign.TOP)
                                .addElement(Button.builder()
                                        .setText("提交")
                                        .setType(ButtonType.PRIMARY)
//                                        .setComplexInteraction(true)
                                        .setActionType(ActionType.FORM_SUBMIT)
                                        .setName("Button_lrztw8x3")
                                        .build())
                                .build())
                        .addColumn(Column.builder()
                                .setWidth("auto")
                                .setVerticalAlign(VerticalAlign.TOP)
                                .addElement(Button.builder()
                                        .setText("取消")
                                        .setType(ButtonType.DEFAULT)
//                                        .setComplexInteraction(true)
                                        .setActionType(ActionType.FORM_RESET)
                                        .setName("Button_lrztw8x4")
                                        .build())
                                .build())
                        .build())
                .build();

        CardHeader cardHeader = CardHeader.of(CardTitle.builder()
                .setTemplate("blue")
                .setTitle(Title.of("下拉选择-多选"))
                .build());

        CardBody cardBody = CardBody.of()
                .add(formContainer);

        CardV2Message message = CardV2Message.builder()
                .setHeader(cardHeader)
                .setBody(cardBody)
                .build();

        String webhook = System.getenv("webhook");
        String secret = System.getenv("secret");
        FeishuRobotClient send = new FeishuRobotClient();
        send.setSecret(secret);
        send.setWebhook(webhook);
        FeishuRobotResponse feiShuRobotResponse = send.sendMessage(message);
        System.out.println(feiShuRobotResponse.getMsg());
    }
}