package com.example.taskflowm.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskflowm.R
import com.example.taskflowm.ui.theme.BrandAccent
import com.example.taskflowm.ui.theme.BrandPrimary
import com.example.taskflowm.ui.theme.TASKFLOWMTheme

@Composable
fun TaskFlowBrand(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.taskflow),
            contentDescription = "TaskFlow Logo",
            modifier = Modifier.size(120.dp),
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = BrandPrimary, fontWeight = FontWeight.Bold)) {
                        append("Task")
                    }
                    withStyle(style = SpanStyle(color = BrandAccent, fontWeight = FontWeight.SemiBold)) {
                        append("Flow")
                    }
                },
                fontSize = 56.sp,
                letterSpacing = (-1).sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskFlowBrandPreview() {
    TASKFLOWMTheme {
        TaskFlowBrand()
    }
}
