package eu.vrtime.webappbase.web;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.ExactLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public abstract class AbstractBasePage extends WebPage {

	private static final long serialVersionUID = -2208496415856192627L;
	
	private static final String FEEDBACK_SUCCESS = "feedbackSuccess";
	private static final String FEEDBACK_ERROR = "feedbackError";
	private static final String FEEDBACK_INFO = "feedbackInfo";

	private FeedbackPanel successFeedbackPanel;
	private FeedbackPanel errorFeedbackPanel;
	private FeedbackPanel infoFeedbackPanel;

	public AbstractBasePage() {
		super();
		successFeedbackPanel = createSuccessFeedbackPanel(FEEDBACK_SUCCESS);
		errorFeedbackPanel = createErrorFeedbackPanel(FEEDBACK_ERROR);
		infoFeedbackPanel = createInfoFeedbackPanel(FEEDBACK_INFO);

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(successFeedbackPanel);
		add(errorFeedbackPanel);
		add(infoFeedbackPanel);

	}

	private FeedbackPanel createSuccessFeedbackPanel(final String id) {
		FeedbackPanel feedback = new FeedbackPanel(id, new ExactLevelFeedbackMessageFilter(FeedbackMessage.ERROR));
		return feedback;
	}

	private FeedbackPanel createErrorFeedbackPanel(final String id) {
		FeedbackPanel feedback = new FeedbackPanel(id, new ErrorLevelFeedbackMessageFilter(FeedbackMessage.WARNING));
		return feedback;
	}

	private FeedbackPanel createInfoFeedbackPanel(final String id) {
		FeedbackPanel feedback = new FeedbackPanel(id, new ExactLevelFeedbackMessageFilter(FeedbackMessage.INFO));
		return feedback;
	}

}
