/**
 * Copyright FMR LLC <opensource@fidelity.com>
 * SPDX-License-Identifier: Apache-2.0
 */

package io.jenkins.plugins.cdevents.util;

import dev.cdevents.constants.CDEventConstants;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.model.Result;
import org.jenkinsci.plugins.workflow.actions.ErrorAction;

@SuppressFBWarnings(value = "DM_CONVERT_CASE",
        justification = "Platform default encoding is OK for these values")
public class OutcomeMapper {

    public static CDEventConstants.Outcome mapResultToOutcome(Result result) {
        switch (result.toString().toUpperCase()) {
            case "SUCCESS":
            case "UNSTABLE":
                return CDEventConstants.Outcome.SUCCESS;
            case "NOT_BUILT":
            case "ABORTED":
                return CDEventConstants.Outcome.FAILURE;
            default: // Jenkins status FAILURE and all others
                return CDEventConstants.Outcome.ERROR;
        }
    }

    public static CDEventConstants.Outcome mapResultToOutcome(ErrorAction error) {
        if (error != null) {
            return CDEventConstants.Outcome.ERROR;
        } else {
            return CDEventConstants.Outcome.SUCCESS;
        }
    }
}
